package com.gbt.dl.ui.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dl_update_master")
public class MasterInsertUpdateDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sequence_id;

	@Column(name = "master_ref_no", nullable = false)
	private int master_ref_no;
	@Column(name = "change_log", nullable = false)
	private String change_log;
	@Column(name = "master_id", nullable = false)
	private String master_id;
	@Column(name = "city_cd", nullable = true)
	private String city_cd;
	@Column(name = "brand_cd", nullable = true)
	private String brand_cd;
	@Column(name = "chain_cd", nullable = true)
	private String chain_cd;
	@Column(name = "chain_nm", nullable = true)
	private String chain_nm;
	@Column(name = "name", nullable = true)
	private String name;
	@Column(name = "address", nullable = true)
	private String address;
	@Column(name = "address2", nullable = true)
	private String address2;
	@Column(name = "address3", nullable = true)
	private String address3;
	@Column(name = "city_nm", nullable = true)
	private String city_nm;
	@Column(name = "state_nm", nullable = true)
	private String state_nm;
	@Column(name = "state_cd", nullable = true)
	private String state_cd;
	@Column(name = "region_nm", nullable = true)
	private String region_nm;
	@Column(name = "iso_num_ctry_cd", nullable = true)
	private String iso_num_ctry_cd;
	@Column(name = "country_cd", nullable = true)
	private String country_cd;
	@Column(name = "country_nm", nullable = true)
	private String country_nm;
	@Column(name = "postal_cd", nullable = true)
	private String postal_cd;
	@Column(name = "phone_ctry_cd", nullable = true)
	private String phone_ctry_cd;
	@Column(name = "phone_no", nullable = true)
	private String phone_no;
	@Column(name = "latitude", nullable = true)
	private String latitude;
	@Column(name = "longitude", nullable = true)
	private String longitude;
	@Column(name = "location_qual", nullable = true)
	private String location_qual;
	@Column(name = "xaxis", nullable = true)
	private String xaxis;
	@Column(name = "yaxis", nullable = true)
	private String yaxis;
	@Column(name = "zaxis", nullable = true)
	private String zaxis;

	@Column(name = "uploaded_by", nullable = true)
	private String uploaded_by;
	@Column(name = "uploaded_ts", nullable = true)
	private String uploaded_ts;

	public String getUploaded_by() {
		return uploaded_by;
	}

	public String getUploaded_ts() {
		return uploaded_ts;
	}

	public void setUploaded_ts(String uploaded_ts) {
		this.uploaded_ts = uploaded_ts;
	}

	public void setUploaded_by(String uploaded_by) {
		this.uploaded_by = uploaded_by;
	}

	public String getChange_log() {
		return change_log;
	}

	public void setChange_log(String change_log) {
		this.change_log = change_log;
	}

	public String getMaster_id() {
		return master_id;
	}

	public void setMaster_id(String master_id) {
		this.master_id = master_id;
	}

	public String getCity_cd() {
		return city_cd;
	}

	public void setCity_cd(String city_cd) {
		this.city_cd = city_cd;
	}

	public String getBrand_cd() {
		return brand_cd;
	}

	public void setBrand_cd(String brand_cd) {
		this.brand_cd = brand_cd;
	}

	public String getChain_cd() {
		return chain_cd;
	}

	public void setChain_cd(String chain_cd) {
		this.chain_cd = chain_cd;
	}

	public String getChain_nm() {
		return chain_nm;
	}

	public void setChain_nm(String chain_nm) {
		this.chain_nm = chain_nm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity_nm() {
		return city_nm;
	}

	public void setCity_nm(String city_nm) {
		this.city_nm = city_nm;
	}

	public String getState_nm() {
		return state_nm;
	}

	public void setState_nm(String state_nm) {
		this.state_nm = state_nm;
	}

	public String getState_cd() {
		return state_cd;
	}

	public void setState_cd(String state_cd) {
		this.state_cd = state_cd;
	}

	public String getRegion_nm() {
		return region_nm;
	}

	public void setRegion_nm(String region_nm) {
		this.region_nm = region_nm;
	}

	public String getIso_num_ctry_cd() {
		return iso_num_ctry_cd;
	}

	public void setIso_num_ctry_cd(String iso_num_ctry_cd) {
		this.iso_num_ctry_cd = iso_num_ctry_cd;
	}

	public String getCountry_cd() {
		return country_cd;
	}

	public void setCountry_cd(String country_cd) {
		this.country_cd = country_cd;
	}

	public String getCountry_nm() {
		return country_nm;
	}

	public void setCountry_nm(String country_nm) {
		this.country_nm = country_nm;
	}

	public String getPostal_cd() {
		return postal_cd;
	}

	public void setPostal_cd(String postal_cd) {
		this.postal_cd = postal_cd;
	}

	public String getPhone_ctry_cd() {
		return phone_ctry_cd;
	}

	public void setPhone_ctry_cd(String phone_ctry_cd) {
		this.phone_ctry_cd = phone_ctry_cd;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLocation_qual() {
		return location_qual;
	}

	public void setLocation_qual(String location_qual) {
		this.location_qual = location_qual;
	}

	public String getXaxis() {
		return xaxis;
	}

	public void setXaxis(String xaxis) {
		this.xaxis = xaxis;
	}

	public String getYaxis() {
		return yaxis;
	}

	public void setYaxis(String yaxis) {
		this.yaxis = yaxis;
	}

	public String getZaxis() {
		return zaxis;
	}

	public void setZaxis(String zaxis) {
		this.zaxis = zaxis;
	}

	public int getMaster_ref_no() {
		return master_ref_no;
	}

	public void setMaster_ref_no(int master_ref_no) {
		this.master_ref_no = master_ref_no;
	}

	public long getSequence_id() {
		return sequence_id;
	}

	public void setSequence_id(long sequence_id) {
		this.sequence_id = sequence_id;
	}

}
