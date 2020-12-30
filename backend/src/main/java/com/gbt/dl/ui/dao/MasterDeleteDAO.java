package com.gbt.dl.ui.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dl_delete_master")
public class MasterDeleteDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sequence_id;

	@Column(name = "master_id", nullable = false)
	private String master_id;
	@Column(name = "new_master_id", nullable = false)
	private String new_master_id;
	@Column(name = "change_log", nullable = false)
	private String change_log;

	@Column(name = "uploaded_by", nullable = true)
	private String uploaded_by;
	@Column(name = "uploaded_ts", nullable = true)
	private String uploaded_ts;

	public String getUploaded_by() {
		return uploaded_by;
	}

	public void setUploaded_by(String uploaded_by) {
		this.uploaded_by = uploaded_by;
	}

	public String getUploaded_ts() {
		return uploaded_ts;
	}

	public void setUploaded_ts(String uploaded_ts) {
		this.uploaded_ts = uploaded_ts;
	}

	public String getMaster_id() {
		return master_id;
	}

	public void setMaster_id(String master_id) {
		this.master_id = master_id;
	}

	public String getNew_master_id() {
		return new_master_id;
	}

	public void setNew_master_id(String new_master_id) {
		this.new_master_id = new_master_id;
	}

	public String getChange_log() {
		return change_log;
	}

	public void setChange_log(String change_log) {
		this.change_log = change_log;
	}

	public long getSEQUENCE_ID() {
		return sequence_id;
	}

	public void setSEQUENCE_ID(long sequence_id) {
		this.sequence_id = sequence_id;
	}

}
