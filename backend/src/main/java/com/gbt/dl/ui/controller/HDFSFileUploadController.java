package com.gbt.dl.ui.controller;



import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gbt.dl.ui.dao.DlFileUploadDAO;
import com.gbt.dl.ui.dto.ResponseDTO;
import com.gbt.dl.ui.model.DlFileUploadService;
import com.gbt.dl.ui.util.HdfsUtil;
import com.gbt.dl.ui.util.Utils;


@RestController
@CrossOrigin(origins = "*")
@EnableAutoConfiguration
public class HDFSFileUploadController {
	
	
private static final Logger logger = LogManager.getLogger(HDFSFileUploadController.class);
	
	@Autowired
	private HdfsUtil hdfsUtil;
	
	
	
	
	@Autowired
	private DlFileUploadService repository;

	@RequestMapping(value = "/uploadToHdfs", method = { RequestMethod.POST })
	public ResponseDTO uploadToHdfs(HttpServletRequest request, @RequestParam("file") MultipartFile file)
	        throws IllegalStateException, IOException {
		ResponseDTO responseDTO = new ResponseDTO();
		logger.info("uploadToHdfs started....");
		if (!file.isEmpty()) {
			logger.info("file.size... " + file.getSize());
			try {
				String originalFilename = file.getOriginalFilename();

				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(originalFilename)));

				out.write(file.getBytes());

				out.flush();
				out.close();

				String destFileName = hdfsUtil.getHdfsProperties().getUploadPath() +"/"+Utils.getDate()+"/"+ originalFilename;
			
				logger.info("destFileName::: " + destFileName);
				
				hdfsUtil.uploadFile(new String[] { originalFilename, destFileName });

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				responseDTO.setStatus(false);
				responseDTO.setMessage("Failed while uploading the file into datalake");
				return responseDTO;
				
			} catch (IOException e) {
				e.printStackTrace();
				responseDTO.setStatus(false);
				responseDTO.setMessage("Failed while uploading the file into datalake");
				return responseDTO;
			}

			responseDTO.setStatus(true);
			responseDTO.setMessage("Successfully Uploaded the file into datalake");
			return responseDTO;

		}
		responseDTO.setStatus(true);
		responseDTO.setMessage("Successfully Uploaded the file into datalake");
		return responseDTO;
	}
	
	@RequestMapping(value = "/dlfileuploadform", method = { RequestMethod.POST })
	public ResponseDTO uploadDlFile(@RequestBody DlFileUploadDAO dlFileUploadDAO){
		
		ResponseDTO responseDTO = new ResponseDTO();
		dlFileUploadDAO.setUploaded_date(Utils.getDateTime());
		repository.save(dlFileUploadDAO);
		
		if(repository.save(dlFileUploadDAO).equals(null)){
			responseDTO.setStatus(false);
			responseDTO.setMessage("Failed while uploading the dl file form data");
			return responseDTO;
		}
		
		
		responseDTO.setStatus(true);
		responseDTO.setMessage("successfully uploaded the dl file form data");
		return responseDTO;
	}
	
	
	
	
	
	
	
	

}
