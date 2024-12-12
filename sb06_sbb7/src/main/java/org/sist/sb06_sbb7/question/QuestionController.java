package org.sist.sb06_sbb7.question;

import java.security.Principal;

import org.sist.sb06_sbb7.answer.AnswerForm;
import org.sist.sb06_sbb7.user.SiteUser;
import org.sist.sb06_sbb7.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

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
	/*
	@GetMapping("/list")
	public void list(Model model
			,@RequestParam(value = "page", defaultValue = "0") int page
			) {
		Page<Question> paging = this.questionService.getList(page);
		model.addAttribute("paging", paging);
	}
	*/
	// [3] 검색기능 포함
	@GetMapping("/list")
	public void list(Model model
			,@RequestParam(value = "page", defaultValue = "0") int page
			,@RequestParam(value = "kw", defaultValue = "") String kw
			) {
		Page<Question> paging = this.questionService.getList(page, kw);
		model.addAttribute("paging", paging);
		model.addAttribute("kw", kw);
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
	
	// 글 수정버튼 클릭 시
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal) {
        Question question = this.questionService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        // 엔티티 -> DTO 변환
        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "/question/create";
    }
	
	// 수정 처리
	@PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm
    		, BindingResult bindingResult, 
            Principal principal
            , @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "/question/create";
        }
        Question question = this.questionService.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }
		
	// 삭제
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.questionService.delete(question);
        return "redirect:/";
    }
	
	// 추천
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String questionVote(Principal principal, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.questionService.vote(question, siteUser);
        return String.format("redirect:/question/detail/%s", id);
    }
	
}
