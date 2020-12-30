package com.gbt.dl.ui.constants;

import java.util.Properties;

public enum Configuration {
	
	
	
	WORKBOOK_NAME("workbook-name"),
	MASTER_INSERT_UPDATE("master-insert-update"),
	MASTER_DELETE("master-delete"),
	VARIANT_UPDATE("variant-update"),
	SHEET_COUNT("sheetcount"),
	SPRING_DATASOURCE_URL("spring.datasource.url"),
	SPRING_DATASOURCE_USERNAME("spring.datasource.username"),
	SPRING_DATASOURCE_PASSWORD("spring.datasource.password");
	
	
	
	
	private static Properties properties;
	static {
		properties = new Properties();
		try {
			properties.load(Configuration.class.getClassLoader().getResourceAsStream("application.properties"));
		} catch (Exception e) {
			throw new RuntimeException("Error when loading configuration file", e);
		}
	}

	private String key;

	Configuration(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return properties.getProperty(key);
	}


}
