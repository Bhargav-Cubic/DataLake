package com.gbt.dl.ui.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_group_access")
public class UserGroupAccessDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sequence_id;

	@Column(name = "username")
	private String username;

	@Column(name = "group_name")
	private String group_name;

	@Column(name = "read_access")
	private String read_access;

	@Column(name = "write_access")
	private String write_access;

	@Column(name = "created_by")
	private String created_by;

	@Column(name = "created_ts")
	private String created_ts;

	@Column(name = "updated_by")
	private String updated_by;

	@Column(name = "lst_upd_dt")
	private String lst_upd_dt;

	public long getSequence_id() {
		return sequence_id;
	}

	public void setSequence_id(long sequence_id) {
		this.sequence_id = sequence_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getRead_access() {
		return read_access;
	}

	public void setRead_access(String read_access) {
		this.read_access = read_access;
	}

	public String getWrite_access() {
		return write_access;
	}

	public void setWrite_access(String write_access) {
		this.write_access = write_access;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public String getCreated_ts() {
		return created_ts;
	}

	public void setCreated_ts(String created_ts) {
		this.created_ts = created_ts;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public String getLst_upd_dt() {
		return lst_upd_dt;
	}

	public void setLst_upd_dt(String lst_upd_dt) {
		this.lst_upd_dt = lst_upd_dt;
	}

}
