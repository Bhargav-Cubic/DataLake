package com.gbt.dl.ui.controller;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbt.dl.ui.constants.AppProperties;
import com.gbt.dl.ui.dao.PCCFormDAO;
import com.gbt.dl.ui.dto.ResponseDTO;
import com.gbt.dl.ui.model.PCCFormService;
import com.gbt.dl.ui.util.Utils;

@RestController
@CrossOrigin(origins = "*")
@EnableAutoConfiguration
public class PCCFormController {

	private static final Logger logger = LogManager.getLogger(PCCFormController.class);

	@Autowired
	PCCFormService repository;
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

	@RequestMapping("/hello")
	public String sayHello() {
		return "hello";
	}

	@PostMapping(value = "/addpcc")
	public ResponseDTO postPCCForm(@RequestBody PCCFormDAO pccFormDao) {
		ResponseDTO responseDTO = new ResponseDTO();

		try {
			Map<String, String> properties = Utils.getProperties(appProperties);
			logger.info("pcc form loading ..............");
			List<String> asList = Arrays.asList(pccFormDao.getPcc().split(","));
			String uploaded_by = pccFormDao.getUploaded_by();
			List<PCCFormDAO> newPcc = new ArrayList<>();
			List<String> existingPcc = new ArrayList<>();
			for (String pcc : asList) {
				boolean existsPcc = repository.exists(pcc);
				if (existsPcc) {
					existingPcc.add(pcc);
					logger.info("********************** Existing pccs are ****************** :" + existingPcc);
				} else {
					PCCFormDAO dao = new PCCFormDAO();
					dao.setPcc(pcc);
					dao.setUploaded_by(uploaded_by);
					dao.setUploaded_ts(Utils.getDate());
					newPcc.add(dao);
				}
			}
			Iterable<PCCFormDAO> savedDao = repository.save(newPcc);

			if (savedDao == null) {
				responseDTO.setStatus(false);
				responseDTO.setMessage(appProperties.getPccFormFailureMessage());
				logger.info(responseDTO);
				return responseDTO;
			} else {
				responseDTO.setStatus(true);
				String failureMsg = 
						existingPcc.isEmpty() ?"": "PCC already exist ("+existingPcc.stream().map(String::toString).collect(Collectors.joining(","))+")";
				String msg = newPcc.isEmpty() ?"":
						"New PCC Saved Successfully, This will be used in Next M&E Run. ";
						
				
				responseDTO.setMessage(msg+"\n"+failureMsg);
				return responseDTO;
			}

		} catch (Exception e) {
			logger.info(e);
			responseDTO.setStatus(false);
			responseDTO.setMessage(appProperties.getPccFormFailureMessage());
			return responseDTO;
		}
	}
}
