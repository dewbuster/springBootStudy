package org.sist.sb06_sbb6.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.sist.sb06_sbb6.exception.DataNotFoundException;
import org.sist.sb06_sbb6.question.Question;
import org.sist.sb06_sbb6.user.SiteUser;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
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
	
	// 답변 조회
    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    // 답변 수정
    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        //this.answerRepository.save(answer);
    }
    
    // 답변 삭제
    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }
	
}
