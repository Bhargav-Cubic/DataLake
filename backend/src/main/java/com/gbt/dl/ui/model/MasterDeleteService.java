package com.gbt.dl.ui.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gbt.dl.ui.dao.MasterDeleteDAO;

@RepositoryRestResource
public interface MasterDeleteService extends CrudRepository<MasterDeleteDAO, Integer> {
	<S extends MasterDeleteDAO> S save(S entity);
}
