package com.gbt.dl.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;	
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class Application {

	private static final Logger logger = LogManager
			.getLogger(Application.class);

	public static void main(String[] args) {

		logger.info("datalake ui is running..................");

		SpringApplication.run(Application.class, args);
	}

	
}
