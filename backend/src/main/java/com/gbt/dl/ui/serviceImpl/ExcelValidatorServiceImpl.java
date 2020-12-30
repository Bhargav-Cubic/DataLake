package com.gbt.dl.ui.serviceImpl;

import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.gbt.dl.ui.constants.AppProperties;
import com.gbt.dl.ui.model.FieldValidatonStatus;
import com.gbt.dl.ui.service.ExcelValidatorService;
import com.gbt.dl.ui.util.ExcelValidator;

@Service
public class ExcelValidatorServiceImpl implements ExcelValidatorService{

	@Override
	public boolean validateSheetHeaders(Workbook wbook,int sheetCount,Map<String,String> properties) {
		// TODO Auto-generated method stub
		return ExcelValidator.validateSheetHeaders(wbook,sheetCount,properties);
	}
	
	@Override
	public boolean validateSheetNames(Workbook wbook,int sheetCount,Map<String,String> properties) {
		// TODO Auto-generated method stub
		return ExcelValidator.validateSheetNames(wbook,sheetCount,properties);
	}

	@Override
	public JSONArray dataExtractor(Sheet sheet,Map<String,String> properties) {
		// TODO Auto-generated method stub
		return ExcelValidator.dataExtractor(sheet,properties);
	}

	@Override
	public FieldValidatonStatus validateMasterInsertUpdateRows(JSONArray jsonArray) {
		// TODO Auto-generated method stub
		return ExcelValidator.isMasterInsertUpdateSheetDataValid(jsonArray);
	}

	@Override
	public boolean validateMasterDeleteRows(JSONArray jsonArray) {
		// TODO Auto-generated method stub
		return ExcelValidator.isMasterDeleteSheetDataValid(jsonArray);
	}

	@Override
	public boolean validateVariantUpdateRows(JSONArray jsonArray) {
		// TODO Auto-generated method stub
		return ExcelValidator.isVariantUpdateSheetDataValid(jsonArray);
	}

	@Override
	public boolean validateWorkBookName(String wBookName,AppProperties appProperties) {
		// TODO Auto-generated method stub
		return ExcelValidator.validateWorkBookName(wBookName, appProperties);
	}

	@Override
	public boolean validateSheetCount(int sheetCount,AppProperties appProperties) {
		// TODO Auto-generated method stub
		return ExcelValidator.validateSheetCount(sheetCount, appProperties);
	}

}
