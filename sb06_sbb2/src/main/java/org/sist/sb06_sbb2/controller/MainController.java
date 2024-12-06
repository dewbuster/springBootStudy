package org.sist.sb06_sbb2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/index")
	public void index() {
		System.out.println("index");
	}
}
