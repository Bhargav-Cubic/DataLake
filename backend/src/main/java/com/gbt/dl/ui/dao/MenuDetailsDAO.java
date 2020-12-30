package com.gbt.dl.ui.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "menu_details")
public class MenuDetailsDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long menu_id;

	@Column(name = "menu_name")
	private String menu_name;

	@Column(name = "parent_menu_id")
	private String parent_menu_id;

	@Column(name = "created_by")
	private String created_by;

	@Column(name = "created_ts")
	private String created_ts;

	@Column(name = "updated_by")
	private String updated_by;

	@Column(name = "lst_upd_dt")
	private String lst_upd_dt;

	public long getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(long menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getParent_menu_id() {
		return parent_menu_id;
	}

	public void setParent_menu_id(String parent_menu_id) {
		this.parent_menu_id = parent_menu_id;
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
