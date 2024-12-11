package org.sist.sb06_sbb5.question;

import java.security.Principal;
import java.util.List;

import org.sist.sb06_sbb5.answer.AnswerForm;
import org.sist.sb06_sbb5.user.SiteUser;
import org.sist.sb06_sbb5.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
	
	private final QuestionService questionService;
	private final UserService userService;
	// private final QuestionRepository questionRepository;

	/*
	@GetMapping("/list")
	public void list(Model model) {
		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList", questionList);
	}
	*/
	// [2]
	@GetMapping("/list")
	public void list(Model model
			,@RequestParam(value = "page", defaultValue = "0") int page
			) {
		Page<Question> paging = this.questionService.getList(page);
		model.addAttribute("paging", paging);
	}
	
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model
			, AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "/question/detail";
	}
	
	// 질문 등록하기
	// 인증 X -> 강제로 로그인 페이지로 이동....
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public void questionCreate(QuestionForm questionForm) {
		
	}

	/*
	@PostMapping("/create")
	public String questionCreate(@RequestParam("subject") String subject, @RequestParam("content") String content) {
		this.questionService.create(subject, content);
		
		return "redirect:/question/list";
	}
	*/
	// 유효성검사
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String questionCreate(
			@Valid QuestionForm questionForm
			, BindingResult bindingResult
			, Principal principal
			) {
		
		if (bindingResult.hasErrors()) {
			return "/question/create";
		}
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.questionService.create(questionForm, siteUser);
		
		return "redirect:/question/list";
	}
	
	
}
