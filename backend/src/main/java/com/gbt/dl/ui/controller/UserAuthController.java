package com.gbt.dl.ui.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbt.dl.ui.model.UserAuthService;




@RestController
@CrossOrigin(origins = "*")
@EnableAutoConfiguration
public class UserAuthController {
	
	private static final Logger logger = LogManager
			.getLogger(UserAuthController.class);
	
	
	@Autowired
	UserAuthService repository;
	
	@PostMapping(path="/loginauth/{username}:{password}")
	public boolean isUserValid(@PathVariable String username,@PathVariable String password){
		
		
		logger.info("We have validating the user name and password");
		
		return repository.existsByUserNameAndPassWord(username, password);
	}
	
	
	

}
