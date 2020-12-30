package com.gbt.dl.ui.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dl_update_variant")
public class VariantUpdateDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sequence_id;

	@Column(name = "variant_id", nullable = false)
	private String variant_id;
	@Column(name = "master_id", nullable = false)
	private String master_id;
	@Column(name = "master_ref_no", nullable = false)
	private String master_ref_no;
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

	public String getVariant_id() {
		return variant_id;
	}

	public void setVariant_id(String variant_id) {
		this.variant_id = variant_id;
	}

	public String getMaster_id() {
		return master_id;
	}

	public void setMaster_id(String master_id) {
		this.master_id = master_id;
	}

	public String getMaster_ref_no() {
		return master_ref_no;
	}

	public void setMaster_ref_no(String master_ref_no) {
		this.master_ref_no = master_ref_no;
	}

	public String getChange_log() {
		return change_log;
	}

	public void setChange_log(String change_log) {
		this.change_log = change_log;
	}

	public long getSequence_id() {
		return sequence_id;
	}

	public void setSequence_id(long sequence_id) {
		this.sequence_id = sequence_id;
	}

}
