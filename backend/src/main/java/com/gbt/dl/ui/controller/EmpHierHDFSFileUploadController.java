package com.gbt.dl.ui.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.gbt.dl.ui.dao.EmpHierDAO;
import com.gbt.dl.ui.dto.ResponseDTO;
import com.gbt.dl.ui.util.HdfsUtil;
import com.gbt.dl.ui.util.Utils;

@RestController
@CrossOrigin(origins = "*")
@EnableAutoConfiguration
public class EmpHierHDFSFileUploadController {
	private static final Logger logger = LogManager.getLogger(EmpHierHDFSFileUploadController.class);
	@Autowired
	private HdfsUtil hdfsUtil;

	@PostMapping(value = "/appendToHdfs")
	public ResponseDTO uploadToHdfs(@RequestBody EmpHierDAO empHierDao) throws IllegalStateException, IOException {
		ResponseDTO responseDTO = new ResponseDTO();
		Configuration conf = new Configuration();
		
		FileSystem fileSystem = FileSystem.get(conf);
		Path hdfsPath = new Path(hdfsUtil.getHdfsProperties().getAppendEmphierPath());
		String destFileName = hdfsUtil.getHdfsProperties().getAppendEmphierPath() + "/" + "emp_hier_config.txt";
		try {
			String records = createRecords(empHierDao);
			System.out.println("records" + records);
			hdfsUtil.appendHdfsFile(new String[] { records, destFileName });
			responseDTO.setStatus(true);
			responseDTO.setMessage("The Config Details are Saved Successfully.");
			return responseDTO;
		} catch (IOException e) { 
			createIfNotExists(responseDTO, fileSystem, hdfsPath);
			String records = createRecords(empHierDao);
			try {
			hdfsUtil.appendHdfsFile(new String[] { records, destFileName });	
			}catch (IOException ex) {
				ex.printStackTrace();
				//admin msg
				responseDTO.setStatus(false);
				responseDTO.setMessage("Backend Error Please contact SysAdmin");
				return responseDTO;
			}
		}  
		catch (Exception e) {
			System.out.println("stacktrace" + e);
			responseDTO.setStatus(false);
			responseDTO.setMessage("Backend Error Please contact SysAdmin");
			return responseDTO;
			//return "failed";
		}
	return responseDTO;
	//	return "success";
	}

	private String createRecords(EmpHierDAO empHierDao) {
		String[] corpIdList = empHierDao.getCorpId().split(",");
		String record = "";
		String records = "";

		for (String values : corpIdList) {
			record = values + "|" + empHierDao.getFluid() + "|" + empHierDao.getHistoryFields() + "|"
					+ empHierDao.getUserName() + "|" + Utils.getDate();
			System.out.println(record);
			records += "\n" + record;
		}
		return records;
	}

	private void createIfNotExists(ResponseDTO responseDTO, FileSystem fileSystem, Path hdfsPath)
			throws IOException {
		if (!fileSystem.exists(hdfsPath)) {
			fileSystem.mkdirs(new Path(hdfsUtil.getHdfsProperties().getAppendEmphierPath()));
			//create hdfs dir
			// hdfsUtil.getHdfsProperties().getAppendEmphierPath();
		}
	}

}
