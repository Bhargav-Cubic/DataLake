package com.gbt.dl.ui.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "user_authentication")
public class UserAuthDAO {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sequence_id;

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String passWord;

	public long getSequence_id() {
		return sequence_id;
	}

	public void setSequence_id(long sequence_id) {
		this.sequence_id = sequence_id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
	

}
