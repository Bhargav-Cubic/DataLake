package com.gbt.dl.ui.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.gbt.dl.ui.constants.AppProperties;
import com.gbt.dl.ui.constants.Configuration;

public class Utils {

	static DateTimeFormatter dateFormat;
	// static DateFormat dateFormat;
	static LocalDateTime now;

	public static File convert(MultipartFile file) {
		File convFile = new File(file.getOriginalFilename());
		try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return convFile;
	}

	public static String getDateTime() {
		dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		now = LocalDateTime.now();
		return dateFormat.format(now);

	}
	public static String getDate(){
		dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		now = LocalDateTime.now();
		return dateFormat.format(now);
	}

	@SuppressWarnings("deprecation")
	public static JSONObject getJSONObject(Row currentRow, String[] header) {
		try {
			JSONObject jsonObj = new JSONObject();

			for (int i = 0; i < header.length; i++) {

				Cell currentCell = currentRow.getCell(i);

				boolean isValueSet = false;

				if (currentCell != null && currentCell.getCellTypeEnum() != CellType._NONE) {
					if (currentCell.getCellTypeEnum() == CellType.STRING) {

						jsonObj.put(header[currentCell.getColumnIndex()], currentCell.getStringCellValue());
						isValueSet = true;

					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {

						jsonObj.put(header[currentCell.getColumnIndex()], currentCell.getNumericCellValue());
						isValueSet = true;
					}
				}

				if (!isValueSet) {
					jsonObj.put(header[i], JSONObject.NULL);
				}

			}

			jsonObj.put("uploaded_ts", Utils.getDateTime());

			return jsonObj;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public static Map<String, String> getProperties(AppProperties appProperties) throws IOException {

		Map<String, String> properties = new HashMap<String, String>();

		properties.put(Configuration.MASTER_INSERT_UPDATE.getKey(), appProperties.getMasterInsertUpdate());
		properties.put(Configuration.MASTER_DELETE.getKey(), appProperties.getMasterDelete());
		properties.put(Configuration.VARIANT_UPDATE.getKey(), appProperties.getVariantUpdate());

		return properties;
	}

}
