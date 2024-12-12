package org.sist.sb06_sbb7.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sist.sb06_sbb7.answer.Answer;
import org.sist.sb06_sbb7.exception.DataNotFoundException;
import org.sist.sb06_sbb7.user.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
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
	public void create(QuestionForm questionForm, SiteUser user) {
		Question question = new Question();
		question.setSubject(questionForm.getSubject());
		question.setContent(questionForm.getContent());
		question.setCreateDate(LocalDateTime.now());
		
		question.setAuthor(user);
		
		this.questionRepository.save(question);
	}
	
	// 페이징 처리 + 목록
	// [1]
	/*
	public Page<Question> getList(int page){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("id"));
		// 추가 2차정렬조건 있다면 add 추가
		//							 pageNumber,pageSize
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.questionRepository.findAll(pageable);
	}
	*/
	
	// 질문 수정
	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		//this.questionRepository.save(question);
	}
	
	// 질문 삭제
	public void delete(Question question) {
        this.questionRepository.delete(question);
    }
	
	// 추천
	public void vote(Question question, SiteUser siteUser) {
		question.getVoter().add(siteUser);
		this.questionRepository.save(question);
	}
	
	// [2] 페이징 처리 + 검색기능
	public Page<Question> getList(int page, String kw){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("id"));
		// 추가 2차정렬조건 있다면 add 추가
		//							 pageNumber,pageSize
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		
		Specification<Question> spec = search(kw);
		
		return this.questionRepository.findAll(spec, pageable);
		
		// return this.questionRepository.findAllByKeyword(kw, pageable);
	}
	
	// Specification
	private Specification<Question> search(String kw) {
		// 익명클래스 객체 생성해서 반환..
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
            	// JPA API -> JPQL == 
                query.distinct(true);  // 중복을 제거 
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return cb.or(
                		cb.like(q.get("subject"), "%" + kw + "%"), // 제목 
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용 
                        cb.like(u1.get("username"), "%" + kw + "%"),    // 질문 작성자 
                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용 
                        cb.like(u2.get("username"), "%" + kw + "%")
                        );   // 답변 작성자 
            }
        };
    }
	
}
