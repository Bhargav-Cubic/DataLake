package com.gbt.dl.ui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PageRefreshController {

	@RequestMapping({ "/add-pcc", "/add-user", "/login", "/hotel-file-upload","/home", "/" })
	public String index() {
		return "forward:/index.html";
	}
}