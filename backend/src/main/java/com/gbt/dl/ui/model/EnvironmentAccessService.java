package com.gbt.dl.ui.model;

import org.springframework.data.repository.CrudRepository;

import com.gbt.dl.ui.dao.EnvironmentAccessDAO;


public interface EnvironmentAccessService extends CrudRepository<EnvironmentAccessDAO, Integer> {

	//List<EnvironmentAccessDAO> findByenvironmentName(String environmentName);
	
	 Iterable<EnvironmentAccessDAO> findAll();               
	
}
