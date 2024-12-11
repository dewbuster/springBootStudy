package org.sist.sb06_sbb5.answer;

import java.security.Principal;

import org.sist.sb06_sbb5.question.Question;
import org.sist.sb06_sbb5.question.QuestionService;
import org.sist.sb06_sbb5.user.SiteUser;
import org.sist.sb06_sbb5.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
	
	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;
	
	/*
	@PostMapping("/create/{id}")
	public String createAnswer(@PathVariable("id") Integer id, @RequestParam("content") String content) {
		Question question = this.questionService.getQuestion(id);
		this.answerService.create(question, content);
		return String.format("redirect:/question/detail/%s",id);
	}
	*/
	// [2]
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create/{id}")
	public String createAnswer(
			@PathVariable("id") Integer id
			, @Valid AnswerForm answerForm
			, BindingResult bindingResult
			, Model model
			, Principal principal
			) {
		
		Question question = this.questionService.getQuestion(id);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		if (bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "/question/detail";
		}
		
		this.answerService.create(question, answerForm.getContent(), siteUser);
		return String.format("redirect:/question/detail/%s",id);
	}
}
