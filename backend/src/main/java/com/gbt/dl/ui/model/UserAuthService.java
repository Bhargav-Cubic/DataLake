package com.gbt.dl.ui.model;

import org.springframework.data.repository.CrudRepository;

import com.gbt.dl.ui.dao.UserAuthDAO;

public interface UserAuthService extends CrudRepository<UserAuthDAO, Integer> {
	
	boolean existsByUserNameAndPassWord(String username,String password);

}
