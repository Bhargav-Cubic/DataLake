package com.gbt.dl.ui.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gbt.dl.ui.dao.EnvironmentAccessDAO;
import com.gbt.dl.ui.model.EnvironmentAccessService;

@RestController
@CrossOrigin(origins = "*")
@EnableAutoConfiguration
public class EnvironmentAccessController {

	private static final Logger logger = LogManager
			.getLogger(EnvironmentAccessController.class);

	@Autowired
	EnvironmentAccessService repository;

	@GetMapping(path = "/envaccess")
	public @ResponseBody Iterable<EnvironmentAccessDAO> getEnvironmentAccess() {
		logger.info("processing the environment access data");
		return repository.findAll();

	}
}
