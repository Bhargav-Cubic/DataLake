package com.gbt.dl.ui.model;

import org.springframework.data.repository.CrudRepository;

import com.gbt.dl.ui.dao.DlFileUploadDAO;

public interface DlFileUploadService extends
		CrudRepository<DlFileUploadDAO, Integer> {

	<S extends DlFileUploadDAO> S save(S entity);

}
