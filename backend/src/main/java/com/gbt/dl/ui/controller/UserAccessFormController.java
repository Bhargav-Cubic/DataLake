package com.gbt.dl.ui.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gbt.dl.ui.constants.AppProperties;
import com.gbt.dl.ui.dao.UserAccessFormDAO;
import com.gbt.dl.ui.dto.ResponseDTO;
import com.gbt.dl.ui.model.UserAccessFormService;
import com.gbt.dl.ui.util.Utils;

@RestController
@CrossOrigin(origins = "*")
@EnableAutoConfiguration
public class UserAccessFormController {

	private static final Logger logger = LogManager.getLogger(UserAccessFormController.class);

	@Autowired
	UserAccessFormService repository;
	@Autowired
	private AppProperties appProperties;

	@Autowired
	public AppProperties getAppProperties() {
		return appProperties;
	}

	@Autowired
	public void setAppProperties(AppProperties appProperties) {
		this.appProperties = appProperties;
	}

	@PostMapping(value = "/adduser")
	public ResponseDTO postUserAccessForm(@RequestBody UserAccessFormDAO userAccessFormDAO) {

		try {
			logger.info("user access form form loading ..............");

			ResponseDTO responseDTO = new ResponseDTO();

			userAccessFormDAO.setUploaded_ts(Utils.getDateTime());
			System.out.println("user access form ..........." + userAccessFormDAO.toString());

			repository.save(userAccessFormDAO);
			if (repository.save(userAccessFormDAO).equals(null)) {
				responseDTO.setStatus(false);
				responseDTO.setMessage(appProperties.getUserAccessFormFailedMessage());
				logger.info(responseDTO);
				return responseDTO;
			} else {

				logger.info(userAccessFormDAO.toString());
				System.out.println(userAccessFormDAO.toString());
				responseDTO.setStatus(true);
				responseDTO.setMessage(appProperties.getUserAccessFormSuccessMessage());
				logger.info(responseDTO.toString());
				return responseDTO;
			}
		} catch (Exception e) {
			logger.info(e);
			ResponseDTO responseDTO = new ResponseDTO();
			responseDTO.setStatus(false);
			responseDTO.setMessage(appProperties.getUserAccessFormFailedMessage());
			return responseDTO;
		}
	}

}
