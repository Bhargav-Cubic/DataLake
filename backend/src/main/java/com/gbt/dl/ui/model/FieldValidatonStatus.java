package com.gbt.dl.ui.model;

public class FieldValidatonStatus {
	
	private boolean fieldStatus;
	private String statusMessage="";
	boolean cityCdStatus=true;
	boolean cityNmStatus=true;
	boolean isoCntryCd=true;
	public boolean isFieldStatus() {
		return fieldStatus;
	}
	public void setFieldStatus(boolean fieldStatus) {
		this.fieldStatus = fieldStatus;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	public void appendStatusMessage(String statusMessage)
	{
		this.statusMessage=this.statusMessage + statusMessage;
	}
	public boolean isCityCdStatus() {
		return cityCdStatus;
	}
	public void setCityCdStatus(boolean cityCdStatus) {
		this.cityCdStatus = cityCdStatus;
	}
	public boolean isCityNmStatus() {
		return cityNmStatus;
	}
	public void setCityNmStatus(boolean cityNmStatus) {
		this.cityNmStatus = cityNmStatus;
	}
	public boolean isIsoCntryCd() {
		return isoCntryCd;
	}
	public void setIsoCntryCd(boolean isoCntryCd) {
		this.isoCntryCd = isoCntryCd;
	}
	
	
	
	
	

}
