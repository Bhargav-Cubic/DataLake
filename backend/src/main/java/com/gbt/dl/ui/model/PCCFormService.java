package com.gbt.dl.ui.model;

import org.springframework.data.repository.CrudRepository;

import com.gbt.dl.ui.dao.PCCFormDAO;

public interface PCCFormService extends CrudRepository<PCCFormDAO, String> {
	<S extends PCCFormDAO> S save(S entity);
}
