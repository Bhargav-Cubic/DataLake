package com.gbt.dl.ui.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@EnableAutoConfiguration
public class GroupMenuAccessController {

	@Autowired
	EntityManager entityManager;

	@GetMapping(path = "/groupmenuitems/{UserName}")
	public List<String> getGroupMenuNames(@PathVariable String UserName) {

		Query query = entityManager
				.createQuery(
						"select md.menu_name from MenuDetailsDAO md join GroupDetailsDAO gd on md.menu_id=gd.menu_id join UserGroupAccessDAO uga on gd.group_name=uga.group_name where uga.username=:uname")
				.setParameter("uname", UserName);

		System.out.println("query result list " + query.getResultList());
		return query.getResultList();

	}

}
