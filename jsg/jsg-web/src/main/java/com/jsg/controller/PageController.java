package com.jsg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("{page}")
	public String index(@PathVariable String page) {
		
		System.out.println(111111);
		return page;
	}
}
