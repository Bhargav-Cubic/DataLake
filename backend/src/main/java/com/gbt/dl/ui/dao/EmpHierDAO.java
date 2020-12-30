package com.gbt.dl.ui.dao;

public class EmpHierDAO {	
	
	private String corpId;
	private String fluid;
	private String userName;
	private String historyFields;
	
	
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}	
	
	public String getFluid() {
		return fluid;
	}
	public void setFluid(String fluid) {
		this.fluid = fluid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public String getHistoryFields() {
		return historyFields;
	}
	public void setHistoryFields(String historyFields) {
		this.historyFields = historyFields;
	}
	@Override
	public String toString() {
		return "EmpHierDAO [corpId=" + corpId + ", fluid=" + fluid
				+ ", userName=" + userName + ", historyFields=" + historyFields
				+ "]";
	}

}
