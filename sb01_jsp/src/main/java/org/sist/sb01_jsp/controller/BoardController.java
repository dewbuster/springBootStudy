package org.sist.sb01_jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/board")
public class BoardController {
	
	@GetMapping("/list")
	public void boardList() {
		System.out.println(">/board/list 컨트롤러 메서드...");
	}

}
