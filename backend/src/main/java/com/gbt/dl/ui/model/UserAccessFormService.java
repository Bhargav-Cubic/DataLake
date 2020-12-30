package com.gbt.dl.ui.model;

import org.springframework.data.repository.CrudRepository;

import com.gbt.dl.ui.dao.UserAccessFormDAO;

public interface UserAccessFormService extends CrudRepository<UserAccessFormDAO, Integer> {
	<S extends UserAccessFormDAO> S save(S entity);
}