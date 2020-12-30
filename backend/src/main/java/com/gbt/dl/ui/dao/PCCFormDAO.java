package com.gbt.dl.ui.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "add_pcc")
public class PCCFormDAO {
	
	
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private long sequence_id;
	 */
	@Id
	@Column(name = "pcc", nullable = false)
	private String pcc;
	
	@Column(name = "uploaded_by", nullable = false)
	private String uploaded_by;	
	
	@Column(name="uploaded_ts",nullable = false)
	private  String uploaded_ts;
    
	public String getUploaded_ts() {
		return uploaded_ts;
	}

	public void setUploaded_ts(String uploaded_ts) {
		this.uploaded_ts = uploaded_ts;
	}

	public String getUploaded_by() {
		return uploaded_by;
	}

	public void setUploaded_by(String uploaded_by) {
		this.uploaded_by = uploaded_by;
	}


	@Override
	public String toString() {
		/*
		 * return "PCCFormDAO [sequence_id=" + sequence_id + ", pcc=" + pcc +
		 * ", uploaded_by=" + uploaded_by + "]";
		 */
		return "PCCFormDAO [ pcc=" + pcc + "]";
	}

	public String getPcc() {
		return pcc;
	}

	public void setPcc(String pcc) {
		this.pcc = pcc;
	}
	
	public PCCFormDAO(String pcc,String uploaded_by ) {
		this.pcc = pcc;
		this.uploaded_by = uploaded_by;
	}
	public PCCFormDAO(){
		
	}
	
}
	