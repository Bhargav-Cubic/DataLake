package com.gbt.dl.ui.controller;

import java.io.File;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gbt.dl.ui.constants.AppProperties;
import com.gbt.dl.ui.constants.Configuration;
import com.gbt.dl.ui.constants.Constants;
import com.gbt.dl.ui.dao.MasterDeleteDAO;
import com.gbt.dl.ui.dao.MasterInsertUpdateDAO;
import com.gbt.dl.ui.dao.VariantUpdateDAO;
import com.gbt.dl.ui.dto.ResponseDTO;
import com.gbt.dl.ui.model.FieldValidatonStatus;
import com.gbt.dl.ui.model.MasterDeleteService;
import com.gbt.dl.ui.model.MasterInsertUpdateService;
import com.gbt.dl.ui.model.VariantUpdateService;
import com.gbt.dl.ui.service.ExcelValidatorService;
import com.gbt.dl.ui.util.Utils;
import com.google.gson.Gson;

@RestController
@CrossOrigin(origins = "*")
@EnableAutoConfiguration
public class HNSFileUploadController {

	private static final Logger logger = LogManager.getLogger(HNSFileUploadController.class);

	@Autowired
	MasterInsertUpdateService masterInsertUpdateService;

	@Autowired
	MasterDeleteService masterDeleteService;

	@Autowired
	VariantUpdateService variantUpdateService;

	@Autowired
	ExcelValidatorService excelValidatorService;

	@Autowired
	private AppProperties appProperties;

	@RequestMapping(value = "/addMasterVariant", method = RequestMethod.POST)
	public ResponseDTO addMasterVariant(@RequestParam("file") MultipartFile file) {

		ResponseDTO responseDTO = new ResponseDTO();
		try {

			Map<String, String> properties = Utils.getProperties(appProperties);

			File convertedFile = Utils.convert(file);
			System.out.println("file name is ...." + convertedFile.getName());

			if (excelValidatorService.validateWorkBookName(convertedFile.getName(), appProperties)) {

				logger.info("Work book name validated successfully");

				Workbook wBook = new XSSFWorkbook(file.getInputStream());

				int sheetCount = wBook.getNumberOfSheets();

				if (excelValidatorService.validateSheetCount(sheetCount, appProperties)) {

					logger.info("Work book sheet count validated successfully");

					// sheet name and header validations ----3
					if (excelValidatorService.validateSheetNames(wBook, sheetCount, properties)) {

						logger.info("work book sheet names validated successfully");

						if (excelValidatorService.validateSheetHeaders(wBook, sheetCount, properties)) {

							logger.info("work book sheets headers validated successfully");
							StringBuilder sb = new StringBuilder();

							JSONArray masterInsertUpdate = excelValidatorService.dataExtractor(
									wBook.getSheet(Configuration.MASTER_INSERT_UPDATE.getKey()), properties);

							System.out.println(
									"length of the sheet masterInsertUpdate sheet" + masterInsertUpdate.length());

							JSONArray masterDelete = excelValidatorService
									.dataExtractor(wBook.getSheet(Configuration.MASTER_DELETE.getKey()), properties);

							JSONArray variantUpdate = excelValidatorService
									.dataExtractor(wBook.getSheet(Configuration.VARIANT_UPDATE.getKey()), properties);

							FieldValidatonStatus isValidMasterInsertUpdateData = excelValidatorService
									.validateMasterInsertUpdateRows(masterInsertUpdate);
							boolean isValidMasterDeleteData = excelValidatorService
									.validateMasterDeleteRows(masterDelete);
							boolean isValidVariantUpdatedata = excelValidatorService
									.validateVariantUpdateRows(variantUpdate);

							System.out.println("masterInsertUpdate.length()" + masterInsertUpdate.length());
							System.out.println("masterDelete.length()" + masterDelete.length());
							System.out.println("variantUpdate.length()" + variantUpdate.length());
							System.out.println("isValidMasterInsertUpdateData.isFieldStatus()"
									+ isValidMasterInsertUpdateData.isFieldStatus());
							System.out.println("isValidMasterDeleteData" + isValidMasterDeleteData);
							System.out.println("isValidVariantUpdatedata" + isValidVariantUpdatedata);

							if (masterInsertUpdate.length() != 0 || masterDelete.length() != 0
									|| variantUpdate.length() != 0) {
								if (isValidMasterInsertUpdateData.isFieldStatus() || isValidMasterDeleteData
										|| isValidVariantUpdatedata) {

									if (masterInsertUpdate.length() != 0
											&& isValidMasterInsertUpdateData.isFieldStatus()) {
										for (int row = 0; row < masterInsertUpdate.length(); row++) {
											JSONObject obj = masterInsertUpdate.getJSONObject(row);
											MasterInsertUpdateDAO masterInsertUpdateDAO = new Gson()
													.fromJson(obj.toString(), MasterInsertUpdateDAO.class);
											logger.info("masterInsertUpdateDAO    :   " + masterInsertUpdateDAO);
											try {
												masterInsertUpdateService.save(masterInsertUpdateDAO);

											} catch (Exception e) {
												e.printStackTrace();
												sb.append("Failed while loading the Master Insert update sheet data")
														.append(",");

											}
										}
										sb.append("Master Insert update sheet data loaded successfully").append(",");
									} else {
										if (masterInsertUpdate.length() == 0) {
											sb.append("Master Insert update sheet doesn't having data").append(",");
										} else if (!isValidMasterInsertUpdateData.isFieldStatus()) {
											sb.append("Master Insert update sheet doesn't having the valid data")
													.append(",");

										}

									}
									if (masterDelete.length() != 0 && isValidMasterDeleteData) {
										for (int row = 0; row < masterDelete.length(); row++) {

											JSONObject obj = masterDelete.getJSONObject(row);
											MasterDeleteDAO masterDeleteDAO = new Gson().fromJson(obj.toString(),
													MasterDeleteDAO.class);

											try {
												masterDeleteService.save(masterDeleteDAO);
											} catch (Exception e) {
												e.printStackTrace();
												sb.append("Failed while loading the Master Delete sheet data");
											}
										}

										sb.append("Master Delete sheet data loaded successfully").append(",");
									} else {
										if (masterDelete.length() == 0) {
											sb.append("Matser Delete sheet doesn't having data").append(",");
										} else if (!isValidMasterDeleteData) {
											sb.append("Master Delete sheet having the invalid data").append(",");
										}
									}
									if (variantUpdate.length() != 0 && isValidVariantUpdatedata) {
										for (int row = 0; row < variantUpdate.length(); row++) {

											JSONObject obj = variantUpdate.getJSONObject(row);
											VariantUpdateDAO variantUpdateDAO = new Gson().fromJson(obj.toString(),
													VariantUpdateDAO.class);

											try {
												variantUpdateService.save(variantUpdateDAO);
											} catch (Exception e) {
												e.printStackTrace();
												sb.append("Failed while loading the Variant Update sheet data" );
											}

										}
										sb.append(" Variant Update sheet data loaded successfully");
									} else {
										if (variantUpdate.length() == 0) {
											sb.append("Variant Update sheet doen't have any data");
										} else if (!isValidVariantUpdatedata) {
											sb.append(" Variant Update sheet having the invalid data");
										}
									}

									wBook.close();
									responseDTO.setStatus(true);
									// responseDTO.setMessage(appProperties.getwBookValidMessage());
									responseDTO.setMessage(sb.toString());
									// responseDTO.setMasterInsertUpdate(null);
									// responseDTO.setMasterDelete(null);
									// responseDTO.setVariantUpdate(null);
									return responseDTO;

								} else {
									wBook.close();
									responseDTO.setStatus(false);

									StringBuilder msg = new StringBuilder();

									if (!isValidMasterInsertUpdateData.isFieldStatus()) {

										// sb.append(appProperties.getMasterInsertUpdateInvalidDataMessage()).append(",");
										msg.append(isValidMasterInsertUpdateData.getStatusMessage()).append(",");

									}
									if (!isValidMasterDeleteData) {

										msg.append(appProperties.getMasterDeleteInvalidDataMessage()).append(",");

									}

									if (!isValidVariantUpdatedata) {

										msg.append(appProperties.getVariantUpdateInvalidDataMessage());

									}

									responseDTO.setMessage(msg.toString());

									responseDTO.setMasterInsertUpdate(Constants.inValidRowsMasterInsertUpdate);
									responseDTO.setMasterDelete(Constants.inValidRowsMasterDelete);
									responseDTO.setVariantUpdate(Constants.inValidRowsVariantUpdate);

									System.out.println("sb.toString()" + msg.toString());

									return responseDTO;

								}

							} else {
								wBook.close();
								responseDTO.setStatus(false);
								responseDTO.setMessage(convertedFile.getName() + "  " + appProperties.getwBookEmpty());
								return responseDTO;

							}

							// Data Parsing for master-delete sheet

						} else {

							responseDTO.setStatus(false);
							StringBuilder sb = new StringBuilder();
							sb.append("Invalid Sheets ");
							for (int header = 0; header < Constants.inValidSheetHeaders.size(); header++) {
								if (Constants.inValidSheetHeaders.get(header).isHeaderPresent()) {

									sb.append(",").append(Constants.inValidSheetHeaders.get(header).getSheetName())
											.append(" having invalid headers");
								} else {
									sb.append(",").append(Constants.inValidSheetHeaders.get(header).getSheetName())
											.append("having no header");
								}
								// sb.append(",");
							}

							responseDTO
									.setMessage(sb.append(appProperties.getwBookInvalidHeaderNameMessage()).toString());
							return responseDTO;
						}

					} else {

						// WorkBook doesn't have a valid sheet name with respect
						// to header.Please download
						// latest
						// template from below.
						responseDTO.setStatus(false);
						StringBuilder sb = new StringBuilder();
						sb.append("Invalid sheet names :, ");
						for (int i = 0; i < Constants.inValidSheetNames.size(); i++) {

							sb.append(Constants.inValidSheetNames.get(i) + " ,");
							// sb.append("\n");
						}
						responseDTO
								.setMessage(sb.append(appProperties.getwBookNameInvalidSheetNameMessage()).toString());
						return responseDTO;
					}

				} else {
					// if workbook has invalid sheet count
					wBook.close();
					responseDTO.setStatus(false);
					responseDTO.setMessage(appProperties.getwBookInvalidSheetCountMessage());
					return responseDTO;
				}

			} else {
				// if workbook name is invalid
				responseDTO.setStatus(false);
				responseDTO.setMessage(appProperties.getwBookNameInvalidMessage());
				return responseDTO;
			}
		} catch (Exception e) {

			logger.info(e);

			responseDTO.setStatus(false);
			responseDTO.setMessage("Some thing went wrong while uploading the file,please try again");
			return responseDTO;

		}

	}

	@Autowired
	public AppProperties getAppProperties() {
		return appProperties;
	}

	@Autowired
	public void setAppProperties(AppProperties appProperties) {
		this.appProperties = appProperties;
	}

}
