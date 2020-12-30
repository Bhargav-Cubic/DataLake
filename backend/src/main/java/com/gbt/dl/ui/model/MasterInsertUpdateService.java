package com.gbt.dl.ui.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gbt.dl.ui.dao.MasterInsertUpdateDAO;

@RepositoryRestResource
public interface MasterInsertUpdateService extends CrudRepository<MasterInsertUpdateDAO, Long> {

	<S extends MasterInsertUpdateDAO> S save(S entity);

}
