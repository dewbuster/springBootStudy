package org.sist.sb06_sbb5.answer;

import java.util.List;

import org.sist.sb06_sbb5.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer>{
	
	// List<Answer> findByQuestion(Question question);
	
	
}
