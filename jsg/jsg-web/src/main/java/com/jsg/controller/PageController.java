package com.jsg.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsg.util.CookieUtil;

@Controller
public class PageController {
	@RequestMapping("{page}")
	public String page(@PathVariable String page) {
		
		System.out.println(111111);
		return page;
	}

}
