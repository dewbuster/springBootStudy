package org.sist.sb04_oracle_mybatis_jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.java.Log;

@Controller
@Log
public class IndexController {
	
	/*
	@GetMapping("/index")
	@ResponseBody
	public String index() {
		return "hello world~";
	}
	*/
	@GetMapping("/index")
	public void index() {
		
	}

}
