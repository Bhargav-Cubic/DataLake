package com.gbt.dl.ui.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gbt.dl.ui.constants.AppProperties;
import com.gbt.dl.ui.constants.Constants;
import com.gbt.dl.ui.dto.ValidSheet;
import com.gbt.dl.ui.model.FieldValidatonStatus;

public class ExcelValidator {

	private static final Logger logger = LogManager
			.getLogger(ExcelValidator.class);

	public static boolean validateSheetHeaders(Workbook wBook, int sheetCount,
			Map<String, String> properties) {

		try {

			Constants.inValidSheetHeaders = new ArrayList<ValidSheet>();

			for (int i = 0; i < sheetCount; i++) {

				Sheet sheet = wBook.getSheetAt(i);

				String originalHeader = properties.get(sheet.getSheetName())
						.toString();

				Row row = sheet.getRow(0);
				int minCellNumber = row.getFirstCellNum();
				int maxCellNumber = row.getLastCellNum();
				String header = "";
				if (row.getLastCellNum() != 0) {

					for (int j = minCellNumber; j < maxCellNumber; j++) {
						Cell cell = row.getCell(j);

						if (j != maxCellNumber - 1)
							header = header.concat(cell.getStringCellValue()
									+ "|");
						else
							header = header.concat(cell.getStringCellValue());
					}

					if (!header.equals(originalHeader)) {
						ValidSheet validSheet = new ValidSheet();
						validSheet.setSheetName(sheet.getSheetName());
						validSheet.setHeaderPresent(true);
						Constants.inValidSheetHeaders.add(validSheet);

					}

				}

				else {

					logger.info(sheet.getSheetName()
							+ " does not have a header");
					ValidSheet validSheet = new ValidSheet();
					validSheet.setSheetName(sheet.getSheetName());

					validSheet.setHeaderPresent(false);
					Constants.inValidSheetHeaders.add(validSheet);
					wBook.close();
				}

			}

			if (Constants.inValidSheetHeaders.size() == 0) {
				logger.info("Wbook has valid workbook name and Sheet count");
				return true;
			} else {
				return false;
			}

		} catch (Exception ioe) {
			ioe.printStackTrace();
			return false;
		}

	}

	// Validating the Work Book name
	public static boolean validateWorkBookName(String fileName,
			AppProperties appProperties) {

		if (fileName.equals(appProperties.getWorkBookName())) {
			return true;

		} else {
			return false;
		}
	}

	public static boolean validateSheetCount(int sheetCount,
			AppProperties appProperties) {
		if (sheetCount == appProperties.getSheetCount()) {
			return true;

		} else {
			return false;
		}
	}

	public static boolean validateSheetNames(Workbook wBook, int sheetCount,
			Map<String, String> properties) {

		Set<String> keys = properties.keySet();

		Constants.inValidSheetNames = new ArrayList<String>();

		for (int i = 0; i < sheetCount; i++) {
			String sheetName = wBook.getSheetName(i);

			if (!sheetNameValidator(sheetName, keys)) {
				Constants.inValidSheetNames.add(sheetName);
			}
		}
		if (Constants.inValidSheetNames.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean sheetNameValidator(String sheetName, Set<String> keys) {
		for (String o : keys) {
			if (o.equals(sheetName)) {
				return true;
			}
		}
		return false;
	}

	public static JSONArray dataExtractor(Sheet sheet,
			Map<String, String> properties) {

		try {
			logger.info("sheet name:" + sheet.getSheetName());
			Iterator<Row> iterator = sheet.iterator();
			iterator.next();
			JSONArray excelRow = new JSONArray();

			// Header lookup for mapping the sheet values
			// if (sheet.getSheetName().equals("master-insert-update")) {

			String headerStr = properties.get(sheet.getSheetName());

			String header[] = headerStr.split("\\|");

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();

				JSONObject jsonObj = Utils.getJSONObject(currentRow, header);
				excelRow.put(jsonObj);
			}
			return excelRow;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static FieldValidatonStatus isMasterInsertUpdateSheetDataValid(
			JSONArray array) {

		Constants.inValidRowsMasterInsertUpdate = new ArrayList<Integer>();
		FieldValidatonStatus fieldValidatonStatus = new FieldValidatonStatus();
		AppProperties appProperties = new AppProperties();

		try {
			if (array.length() > 0) {

				for (int row = 0; row < array.length(); row++) {
					JSONObject obj = array.getJSONObject(row);
					System.out.println(obj);
					System.out.println(obj.has("master_ref_no"));
					if (obj.get("master_ref_no") != JSONObject.NULL
							&& obj.get("change_log") != JSONObject.NULL
							&& obj.get("master_id") != JSONObject.NULL
							&& obj.get("uploaded_by") != JSONObject.NULL) {

					} else {
						Constants.inValidRowsMasterInsertUpdate.add(row + 2);
						fieldValidatonStatus.setFieldStatus(false);
						fieldValidatonStatus
								.setStatusMessage("Mandatory columns are not filled in the sheet master-insert-update sheet");
						return fieldValidatonStatus;

					}
				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		fieldValidatonStatus.setFieldStatus(true);
		fieldValidatonStatus.setStatusMessage("Success Master Insert Update");
		return fieldValidatonStatus;

		/*if (Constants.inValidRowsMasterInsertUpdate.size() == 0)
			return true;
		else
			return false;*/
	}

	public static boolean isMasterDeleteSheetDataValid(JSONArray array) {

		Constants.inValidRowsMasterDelete = new ArrayList<Integer>();
		try {
			if (array.length() > 0) {
				for (int row = 0; row < array.length(); row++) {
					JSONObject obj = array.getJSONObject(row);

					if (obj.get("new_master_id") != JSONObject.NULL
							&& obj.get("change_log") != JSONObject.NULL
							&& obj.get("master_id") != JSONObject.NULL
							&& obj.get("uploaded_by") != JSONObject.NULL) {

					} else {
						Constants.inValidRowsMasterDelete.add(row + 2);
						return false;
					}
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return true;
		// if (Constants.inValidRowsMasterDelete.size() == 0)
		// return true;
		// else
		// return false;
	}

	public static boolean isVariantUpdateSheetDataValid(JSONArray array) {

		Constants.inValidRowsVariantUpdate = new ArrayList<Integer>();
		try {
			if (array.length() > 0) {
				for (int row = 0; row < array.length(); row++) {
					JSONObject obj = array.getJSONObject(row);

					if (obj.get("variant_id") != JSONObject.NULL
							&& obj.get("change_log") != JSONObject.NULL
							&& obj.get("uploaded_by") != JSONObject.NULL
							&& (obj.get("master_id") != JSONObject.NULL || obj
									.get("master_ref_no") != JSONObject.NULL)) {

					} else {
						Constants.inValidRowsVariantUpdate.add(row + 2);
						return false;
					}
				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return true;

		// if (Constants.inValidRowsVariantUpdate.size() == 0)
		// return true;
		// else
		// return false;
	}

}
