package com.gbt.dl.ui.dto;

import java.util.List;



public class ResponseDTO {

	private boolean status;

	private String message;

	private List<Integer> masterInsertUpdate;

	private List<Integer> masterDelete;

	private List<Integer> variantUpdate;
	
	private String pccFormData;
	
	private String userAccessFormData;

	public String getUserAccessFormData() {
		return userAccessFormData;
	}

	public void setUserAccessFormData(String userAccessFormData) {
		this.userAccessFormData = userAccessFormData;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Integer> getMasterInsertUpdate() {
		return masterInsertUpdate;
	}

	public void setMasterInsertUpdate(List<Integer> masterInsertUpdate) {
		this.masterInsertUpdate = masterInsertUpdate;
	}

	public List<Integer> getMasterDelete() {
		return masterDelete;
	}

	public void setMasterDelete(List<Integer> masterDelete) {
		this.masterDelete = masterDelete;
	}

	public List<Integer> getVariantUpdate() {
		return variantUpdate;
	}

	public void setVariantUpdate(List<Integer> variantUpdate) {
		this.variantUpdate = variantUpdate;
	}

	public String getPccFormData() {
		return pccFormData;
	}

	public void setPccFormData(String pccFormData) {
		this.pccFormData = pccFormData;
	}

}
