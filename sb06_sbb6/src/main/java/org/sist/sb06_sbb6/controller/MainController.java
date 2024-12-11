package org.sist.sb06_sbb6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping(value = {"/", "/index"})
	public String index() {
		System.out.println("index");
		
		return "/index";
	}
}
