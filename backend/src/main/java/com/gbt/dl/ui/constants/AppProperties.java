package com.gbt.dl.ui.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class AppProperties {

	@Value("${master-insert-update}")
	private String masterInsertUpdate;

	@Value("${master-delete}")
	private String masterDelete;

	@Value("${variant-update}")
	private String variantUpdate;

	@Value("${sheetcount}")
	private int sheetCount;

	@Value("${workbook-name}")
	private String workBookName;

	@Value("${spring.datasource.url}")
	private String springDataSourceUrl;

	@Value("${spring.datasource.username}")
	private String springDatasourceUsername;

	@Value("${spring.datasource.password}")
	private String springDatasourcePassword;

	@Value("${wbook_valid_message}")
	private String wBookValidMessage;

	@Value("${wbook_name_invalid_message}")
	private String wBookNameInvalidMessage;

	@Value("${wbook_invalid_sheet_count_message}")
	private String wBookInvalidSheetCountMessage;

	@Value("${master_insert_update_invalid_data_message}")
	private String masterInsertUpdateInvalidDataMessage;

	@Value("${master_delete_invalid_data_message}")
	private String masterDeleteInvalidDataMessage;

	@Value("${variant_update_invalid_data_message}")
	private String variantUpdateInvalidDataMessage;

	@Value("${wbook_name_invalid_sheet_name_message}")
	private String wBookNameInvalidSheetNameMessage;

	@Value("${wbook_invalid_header_name_message}")
	private String wBookInvalidHeaderNameMessage;

	@Value("${wbook_empty}")
	private String wBookEmpty;

	@Value("${pccForm_succes_message}")
	private String pccFormSuccesMessage;

	@Value("${pccForm_failure_message}")
	private String pccFormFailureMessage;

	@Value("${user_access_form_success_message}")
	private String userAccessFormSuccessMessage;

	@Value("${user_access_form_failed_message}")
	private String userAccessFormFailedMessage;
	
	/*@Value("${spring.hadoop.fsUri}")
	private String hdfsUri;
	
	@Value("${spring.hadoop.fsshell}")
	private String fsShell;*/

	public String getUserAccessFormSuccessMessage() {
		return userAccessFormSuccessMessage;
	}

	public void setUserAccessFormSuccessMessage(
			String userAccessFormSuccessMessage) {
		this.userAccessFormSuccessMessage = userAccessFormSuccessMessage;
	}

	public String getUserAccessFormFailedMessage() {
		return userAccessFormFailedMessage;
	}

	public void setUserAccessFormFailedMessage(
			String userAccessFormFailedMessage) {
		this.userAccessFormFailedMessage = userAccessFormFailedMessage;
	}

	public String getPccFormSuccesMessage() {
		return pccFormSuccesMessage;
	}

	public void setPccFormSuccesMessage(String pccFormSuccesMessage) {
		this.pccFormSuccesMessage = pccFormSuccesMessage;
	}

	public String getPccFormFailureMessage() {
		return pccFormFailureMessage;
	}

	public void setPccFormFailureMessage(String pccFormFailureMessage) {
		this.pccFormFailureMessage = pccFormFailureMessage;
	}

	public String getwBookEmpty() {
		return wBookEmpty;
	}

	public void setwBookEmpty(String wBookEmpty) {
		this.wBookEmpty = wBookEmpty;
	}

	public String getwBookInvalidHeaderNameMessage() {
		return wBookInvalidHeaderNameMessage;
	}

	public void setwBookInvalidHeaderNameMessage(
			String wBookInvalidHeaderNameMessage) {
		this.wBookInvalidHeaderNameMessage = wBookInvalidHeaderNameMessage;
	}

	public String getMasterInsertUpdate() {
		return masterInsertUpdate;
	}

	public void setMasterInsertUpdate(String masterInsertUpdate) {
		this.masterInsertUpdate = masterInsertUpdate;
	}

	public String getMasterDelete() {
		return masterDelete;
	}

	public void setMasterDelete(String masterDelete) {
		this.masterDelete = masterDelete;
	}

	public String getVariantUpdate() {
		return variantUpdate;
	}

	public void setVariantUpdate(String variantUpdate) {
		this.variantUpdate = variantUpdate;
	}

	public int getSheetCount() {
		return sheetCount;
	}

	public void setSheetCount(int sheetCount) {
		this.sheetCount = sheetCount;
	}

	public String getWorkBookName() {
		return workBookName;
	}

	public void setWorkBookName(String workBookName) {
		this.workBookName = workBookName;
	}

	public String getSpringDataSourceUrl() {
		return springDataSourceUrl;
	}

	public void setSpringDataSourceUrl(String springDataSourceUrl) {
		this.springDataSourceUrl = springDataSourceUrl;
	}

	public String getSpringDatasourceUsername() {
		return springDatasourceUsername;
	}

	public void setSpringDatasourceUsername(String springDatasourceUsername) {
		this.springDatasourceUsername = springDatasourceUsername;
	}

	public String getSpringDatasourcePassword() {
		return springDatasourcePassword;
	}

	public void setSpringDatasourcePassword(String springDatasourcePassword) {
		this.springDatasourcePassword = springDatasourcePassword;
	}

	public String getwBookValidMessage() {
		return wBookValidMessage;
	}

	public void setwBookValidMessage(String wBookValidMessage) {
		this.wBookValidMessage = wBookValidMessage;
	}

	public String getwBookNameInvalidMessage() {
		return wBookNameInvalidMessage;
	}

	public void setwBookNameInvalidMessage(String wBookNameInvalidMessage) {
		this.wBookNameInvalidMessage = wBookNameInvalidMessage;
	}

	public String getwBookInvalidSheetCountMessage() {
		return wBookInvalidSheetCountMessage;
	}

	public void setwBookInvalidSheetCountMessage(
			String wBookInvalidSheetCountMessage) {
		this.wBookInvalidSheetCountMessage = wBookInvalidSheetCountMessage;
	}

	public String getMasterInsertUpdateInvalidDataMessage() {
		return masterInsertUpdateInvalidDataMessage;
	}

	public void setMasterInsertUpdateInvalidDataMessage(
			String masterInsertUpdateInvalidDataMessage) {
		this.masterInsertUpdateInvalidDataMessage = masterInsertUpdateInvalidDataMessage;
	}

	public String getMasterDeleteInvalidDataMessage() {
		return masterDeleteInvalidDataMessage;
	}

	public void setMasterDeleteInvalidDataMessage(
			String masterDeleteInvalidDataMessage) {
		this.masterDeleteInvalidDataMessage = masterDeleteInvalidDataMessage;
	}

	public String getVariantUpdateInvalidDataMessage() {
		return variantUpdateInvalidDataMessage;
	}

	public void setVariantUpdateInvalidDataMessage(
			String variantUpdateInvalidDataMessage) {
		this.variantUpdateInvalidDataMessage = variantUpdateInvalidDataMessage;
	}

	public String getwBookNameInvalidSheetNameMessage() {
		return wBookNameInvalidSheetNameMessage;
	}

	public void setwBookNameInvalidSheetNameMessage(
			String wBookNameInvalidSheetNameMessage) {
		this.wBookNameInvalidSheetNameMessage = wBookNameInvalidSheetNameMessage;
	}

	/*public String getHdfsUri() {
		return hdfsUri;
	}

	public void setHdfsUri(String hdfsUri) {
		this.hdfsUri = hdfsUri;
	}

	public String getFsShell() {
		return fsShell;
	}

	public void setFsShell(String fsShell) {
		this.fsShell = fsShell;
	}*/
	

}
