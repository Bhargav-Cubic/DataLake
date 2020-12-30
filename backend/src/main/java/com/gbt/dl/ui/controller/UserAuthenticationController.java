package com.gbt.dl.ui.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*")
@EnableAutoConfiguration
public class UserAuthenticationController {

	public static final String REST_SERVICE_URI = "http://ldap-authenticate-gdd.cnapps.gbt.gbtad.com/authenticate";

	@PostMapping("/login/{username}:{password}")
	public String loginAuthentication(@PathVariable String username,
			@PathVariable String password) {

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.postForEntity(
				REST_SERVICE_URI + "?" + "username=" + username + "&"
						+ "password=" + password, null, String.class);

		//HttpStatus status = response.getStatusCode();
		String restCall = response.getBody();

		return restCall;

	}

}
