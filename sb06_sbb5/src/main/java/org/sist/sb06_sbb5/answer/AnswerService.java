package org.sist.sb06_sbb5.answer;

import java.time.LocalDateTime;

import org.sist.sb06_sbb5.question.Question;
import org.sist.sb06_sbb5.user.SiteUser;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {
	
	private final AnswerRepository answerRepository;
	
	public void create(Question question, String content, SiteUser author) {
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setAuthor(author);
		this.answerRepository.save(answer);
	}
	
}
