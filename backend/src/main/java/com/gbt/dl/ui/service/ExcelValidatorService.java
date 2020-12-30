package com.gbt.dl.ui.service;

import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;

import com.gbt.dl.ui.constants.AppProperties;
import com.gbt.dl.ui.model.FieldValidatonStatus;

public interface ExcelValidatorService {
	
	public boolean validateWorkBookName(String wBookName,AppProperties appProperties);
	
	public boolean validateSheetCount(int sheetCount,AppProperties appProperties);
	
	public boolean validateSheetHeaders(Workbook wbook,int sheetCount,Map<String,String> properties) throws Exception;
	
	public boolean validateSheetNames(Workbook wbook,int sheetCount,Map<String,String> properties) throws Exception;
	
	public JSONArray dataExtractor(Sheet sheet,Map<String,String> properties) throws Exception;
	
	public FieldValidatonStatus validateMasterInsertUpdateRows(JSONArray jsonArray) throws Exception;
	
	public boolean validateMasterDeleteRows(JSONArray jsonArray) throws Exception;
	
	public boolean validateVariantUpdateRows(JSONArray jsonArray) throws Exception;
	
	

}
