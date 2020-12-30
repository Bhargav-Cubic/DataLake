package com.gbt.dl.ui.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gbt.dl.ui.dao.VariantUpdateDAO;

@RepositoryRestResource
public interface VariantUpdateService extends CrudRepository<VariantUpdateDAO, Integer> {

	<S extends VariantUpdateDAO> S save(S entity);

}
