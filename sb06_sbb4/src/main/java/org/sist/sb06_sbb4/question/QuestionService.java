package org.sist.sb06_sbb4.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sist.sb06_sbb4.exception.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	// 질문 목록 + 페이징 X 
	public List<Question> getList(){
		return this.questionRepository.findAll();
	}
	
	// id 해당 질문
	public Question getQuestion(Integer id) {
		Optional<Question> optional = this.questionRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			// 강제로 사용자 정의 예외를 발생시키겠다.
			// exception 패키지 + DataNotFoundException 예외 클래스 추가
			throw new DataNotFoundException("question not found");
			
		}
	}
	
	// 질문 등록
	public void create(QuestionForm questionForm) {
		Question question = new Question();
		question.setSubject(questionForm.getSubject());
		question.setContent(questionForm.getContent());
		question.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(question);
	}
	
	// 페이징 처리 + 목록
	public Page<Question> getList(int page){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("id"));
		// 추가 2차정렬조건 있다면 add 추가
		//							 pageNumber,pageSize
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.questionRepository.findAll(pageable);
	}
	
}
