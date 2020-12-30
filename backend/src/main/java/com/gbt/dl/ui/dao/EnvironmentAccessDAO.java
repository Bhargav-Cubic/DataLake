package com.gbt.dl.ui.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "environment_access")
public class EnvironmentAccessDAO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sequence_id;
	
	public long getSequence_id() {
		return sequence_id;
	}

	public void setSequence_id(long sequence_id) {
		this.sequence_id = sequence_id;
	}

	@Column(name = "environment_name")
	private String environmentName;
	
	@Column(name="schema_name")
	private String schemaName;

	public String getEnvironmentName() {
		return environmentName;
	}

	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	

}
