package org.sist.sb06_sbb2.question;

import java.util.List;
import java.util.Optional;

import org.sist.sb06_sbb2.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	public List<Question> getList(){
		return this.questionRepository.findAll();
	}
	
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
	
}
