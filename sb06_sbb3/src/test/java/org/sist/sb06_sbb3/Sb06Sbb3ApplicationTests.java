package org.sist.sb06_sbb3;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.sist.sb06_sbb3.answer.Answer;
import org.sist.sb06_sbb3.answer.AnswerRepository;
import org.sist.sb06_sbb3.question.Question;
import org.sist.sb06_sbb3.question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class Sb06Sbb3ApplicationTests {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	/*
	@Test
	void testJpa() {
		
		// 질문 등록 테스트
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("스프링부트가 무엇인가요?");
		q2.setContent("id는 자동으로 생성되나요?.");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
		
	}
	*/
	/*
	@Test
	void testJpa() {
		// 모든 질문 조회
		List<Question> list = this.questionRepository.findAll();
				
		System.out.println("> " + list.size());
		
		list.stream().forEach(q->System.out.println(q.getSubject()));
	}
	*/
	/*
	@Test
	void testJpa() {
		// 질문 ID에 해당하는 질문을 조회
		Optional<Question> optional = this.questionRepository.findById(2);
		if (optional.isPresent()) { // 값이 존재 한다면
			Question q = optional.get();
			System.out.println(q.getSubject() + "/" + q.getContent());
	} // if
	*/
	/*
	@Test
	void testJpa() {
		// Repository
		// CrudRepository
		// PagingAndSorRepository
		// JpaRepository
		Question q = this.questionRepository.findBySubject("스프링부트가 무엇인가요?");
		System.out.println(q.getContent());
		
	} // if
	*/
	/*
	@Test
	void testJpa() {
		// Repository
		// CrudRepository
		// PagingAndSorRepository
		// JpaRepository
		List<Question> list = this.questionRepository.findBySubjectContaining("sbb");
		System.out.println(list.size());
		
	} // if
	 */
	/*
	@Test
	void testJpa() {
		
		Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		System.out.println(q.getId());
		
	} // if
	*/
	/*
	@Test
	void testJpa() {
		
		List<Question> list = this.questionRepository.findBySubjectLike("%sbb%");
		System.out.println(list.size());
	} // if
	*/
	/*
	@Test
	void testJpa() {
		// 질문 수정
		Optional<Question> optional = this.questionRepository.findById(1);
		if (optional.isPresent()) {
			Question q1 = optional.get();
			q1.setSubject("수정된 제목");
			this.questionRepository.save(q1);
		}
	} // if
	*/
	/*
	@Test
	void testJpa() {
		// 삭제 전 질문수
		System.out.println(this.questionRepository.count());
		
		// this.questionRepository.deleteById(1);
		Optional<Question> oq = this.questionRepository.findById(1);
		Question q1 = oq.get();
		this.questionRepository.delete(q1);
		// 삭제후 질문 수
		System.out.println(this.questionRepository.count());
	} // if
	*/
	
	//////////////////////////////////////////////////////////////////////////////////////////
	// 1. 답변
	
	@Autowired
	private AnswerRepository answerRepository;
	
	/*
	@Test
	void testJpa() {
		
		Optional<Question> optional = this.questionRepository.findById(2);
		if (optional.isPresent()) {
			Question q = optional.get();
			
			Answer a = new Answer();
			a.setContent("2번째 답글");
			a.setCreateDate(LocalDateTime.now());
			
			a.setQuestion(q);
			this.answerRepository.save(a);
		}
		
	}
	*/
	// 데이터를 가져오는방식
	// 1. 즉시방식 (Eager)
	// 2. 지연 방식 (Lazy)
	@Transactional
	@Test
	void testJpa() {
		// 질문2의 모든 답변글 조회
		
		Optional<Question> optional = this.questionRepository.findById(2);
		if (optional.isPresent()) {
			Question q = optional.get();
			List<Answer> list = q.getAnswerList();
			
			list.stream().forEach(a -> System.out.println(a.getContent()));
		}
		
	}
	
}
