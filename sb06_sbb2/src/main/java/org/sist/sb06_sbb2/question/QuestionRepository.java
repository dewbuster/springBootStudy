package org.sist.sb06_sbb2.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Integer>{

	// CRUD 메서드 이미 내장...
	Question findBySubject(String subject);

	// 1. Query Method
	// 약속된 이름으로 메서드 이름을 지정하면 쿼리 자동 생성
	// find컬럼명Like
	// find컬럼명Containing
	List<Question> findBySubjectContaining(String subject);

	//@Query 객체로 select 하는것임
	@Query("SELECT q FROM Question q WHERE q.subject LIKE %:keyword%")
	List<Question> searchBySubject(@Param("subject") String subject);

	@Query(value = "SELECT * FROM question WHERE subject LIKE %:subject%", nativeQuery = true)
	List<Question> searchBySubjectNative(@Param("subject") String subject);
	
	List<Question> findBySubjectLike(String subject);
	
	// WHERE subject=? AND content = ?
	Question findBySubjectAndContent(String subject, String content);


}
