package com.gbt.dl.ui.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dl_fileupload")
public class DlFileUploadDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sequence_id;

	@Column(name = "clientdetails")
	private String clientdetails;

	@Column(name = "typeoffile")
	private String typeoffile;
	
	@Column(name="file_description")
	private String file_description;

	@Column(name = "fileformat")
	private String fileformat;

	@Column(name = "isfileupload")
	private String isfileupload;

	@Column(name = "mftfilefrequency")
	private String mftfilefrequency;

	@Column(name = "mftfileprefix")
	private String mftfileprefix;

	@Column(name = "purgefrequency")
	private String purgefrequency;

	@Column(name = "filename")
	private String filename;

	@Column(name = "filepath")
	private String filepath;

	@Column(name = "tnc")
	private String tnc;

	@Column(name = "uploaded_date")
	private String uploaded_date;

	@Column(name = "uploaded_by")
	private String uploaded_by;

	
	
	
	
	
	
	





	public long getSequence_id() {
		return sequence_id;
	}













	public void setSequence_id(long sequence_id) {
		this.sequence_id = sequence_id;
	}













	public String getClientdetails() {
		return clientdetails;
	}













	public void setClientdetails(String clientdetails) {
		this.clientdetails = clientdetails;
	}













	













	












	public String getTypeoffile() {
		return typeoffile;
	}













	public void setTypeoffile(String typeoffile) {
		this.typeoffile = typeoffile;
	}













	public String getFileformat() {
		return fileformat;
	}













	public void setFileformat(String fileformat) {
		this.fileformat = fileformat;
	}













	public String getIsfileupload() {
		return isfileupload;
	}













	public void setIsfileupload(String isfileupload) {
		this.isfileupload = isfileupload;
	}













	public String getMftfilefrequency() {
		return mftfilefrequency;
	}













	public void setMftfilefrequency(String mftfilefrequency) {
		this.mftfilefrequency = mftfilefrequency;
	}













	public String getMftfileprefix() {
		return mftfileprefix;
	}













	public void setMftfileprefix(String mftfileprefix) {
		this.mftfileprefix = mftfileprefix;
	}













	public String getPurgefrequency() {
		return purgefrequency;
	}













	public void setPurgefrequency(String purgefrequency) {
		this.purgefrequency = purgefrequency;
	}













	public String getFilename() {
		return filename;
	}













	public void setFilename(String filename) {
		this.filename = filename;
	}













	public String getFilepath() {
		return filepath;
	}













	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}













	public String getTnc() {
		return tnc;
	}













	public void setTnc(String tnc) {
		this.tnc = tnc;
	}













	public String getUploaded_date() {
		return uploaded_date;
	}













	public void setUploaded_date(String uploaded_date) {
		this.uploaded_date = uploaded_date;
	}













	public String getUploaded_by() {
		return uploaded_by;
	}













	public void setUploaded_by(String uploaded_by) {
		this.uploaded_by = uploaded_by;
	}



	public String getFile_description() {
		return file_description;
	}













	public void setFile_description(String file_description) {
		this.file_description = file_description;
	}










	@Override
	public String toString() {
		return "DlFileUploadDAO [sequence_id=" + sequence_id
				+ ", clientdetails=" + clientdetails + ", typeoffile="
				+ typeoffile + ", fileformat=" + fileformat
				+ ", file_description=" + file_description + ", isfileupload="
				+ isfileupload + ", mftfilefrequency=" + mftfilefrequency
				+ ", mftfileprefix=" + mftfileprefix + ", purgefrequency="
				+ purgefrequency + ", filename=" + filename + ", filepath="
				+ filepath + ", tnc=" + tnc + ", uploaded_by=" + uploaded_by + "]";
	}













	public DlFileUploadDAO(long sequence_id, String clientdetails,
			String typeoffile, String fileformat, String file_description,
			String isfileupload, String mftfilefrequency, String mftfileprefix,
			String purgefrequency, String filename, String filepath,
			String tnc, String uploaded_date, String uploaded_by) {
		super();
		this.sequence_id = sequence_id;
		this.clientdetails = clientdetails;
		this.typeoffile = typeoffile;
		this.fileformat = fileformat;
		this.file_description = file_description;
		this.isfileupload = isfileupload;
		this.mftfilefrequency = mftfilefrequency;
		this.mftfileprefix = mftfileprefix;
		this.purgefrequency = purgefrequency;
		this.filename = filename;
		this.filepath = filepath;
		this.tnc = tnc;
		this.uploaded_date = uploaded_date;
		this.uploaded_by = uploaded_by;
	}













	public DlFileUploadDAO() {

	}
	

}
