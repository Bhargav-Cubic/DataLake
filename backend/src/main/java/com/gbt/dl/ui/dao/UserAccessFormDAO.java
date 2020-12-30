package com.gbt.dl.ui.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_access")
public class UserAccessFormDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sequence_id;

	@Column(name = "gbt_id", nullable = false)
	private String gbt_id;

	@Column(name = "first_name", nullable = false)
	private String first_name;

	@Column(name = "last_name", nullable = false)
	private String last_name;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "department", nullable = true)
	private String department;

	@Column(name = "job_description", nullable = true)
	private String job_description;

	@Column(name = "requested_by", nullable = false)
	private String requested_by;

	@Column(name = "user_type", nullable = false)
	private String user_type;

	@Column(name = "pii_access", nullable = false)
	private String pii_access;

	@Column(name = "development", nullable = true)
	private String development;
	
	@Column(name = "production", nullable = true)
	private String production;
	
	@Column(name = "sandbox", nullable = true)
	private String sandbox;	

	public String getDevelopment() {
		return development;
	}

	public void setDevelopment(String development) {
		this.development = development;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public String getSandbox() {
		return sandbox;
	}

	public void setSandbox(String sandbox) {
		this.sandbox = sandbox;
	}

	@Column(name = "reason", nullable = false)
	private String reason;

	public long getSequence_id() {
		return sequence_id;
	}

	public void setSequence_id(long sequence_id) {
		this.sequence_id = sequence_id;
	}

	public String getGbt_id() {
		return gbt_id;
	}

	public void setGbt_id(String gbt_id) {

		this.gbt_id = gbt_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getJob_description() {
		return job_description;
	}

	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}

	public String getRequested_by() {
		return requested_by;
	}

	public void setRequested_by(String requested_by) {
		this.requested_by = requested_by;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getPii_access() {
		return pii_access;
	}

	public void setPii_access(String pii_access) {
		this.pii_access = pii_access;
	}

	

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getUploaded_ts() {
		return uploaded_ts;
	}

	public void setUploaded_ts(String uploaded_ts) {
		this.uploaded_ts = uploaded_ts;
	}

	@Column(name = "uploaded_ts", nullable = false)
	private String uploaded_ts;

	

	@Override
	public String toString() {
		return "UserAccessFormDAO [sequence_id=" + sequence_id + ", gbt_id="
				+ gbt_id + ", first_name=" + first_name + ", last_name="
				+ last_name + ", email=" + email + ", department=" + department
				+ ", job_description=" + job_description + ", requested_by="
				+ requested_by + ", user_type=" + user_type + ", pii_access="
				+ pii_access + ", development=" + development + ", production="
				+ production + ", sandbox=" + sandbox + ", reason=" + reason
				+ ", uploaded_ts=" + uploaded_ts + "]";
	}

	public UserAccessFormDAO(long sequence_id, String gbt_id, String first_name, String last_name, String email,
			String department, String job_description, String requested_by, String user_type, String pii_access,
			String development,String production,String sandbox, String reason) {

		this.sequence_id = sequence_id;
		this.gbt_id = gbt_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.department = department;
		this.job_description = job_description;
		this.requested_by = requested_by;
		this.reason = reason;
		this.user_type = user_type;
		this.pii_access = pii_access;
		this.development = development;
		this.sandbox=sandbox;
		this.production=production;

	}

	public UserAccessFormDAO() {

	}

}
