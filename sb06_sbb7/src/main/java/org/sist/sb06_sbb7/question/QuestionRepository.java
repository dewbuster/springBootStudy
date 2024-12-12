package org.sist.sb06_sbb7.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
	
	// 페이징 처리(암기)
	Page<Question> findAll(Pageable pageable);
	
	// 페이징 처리 + 검색
	Page<Question> findAll(Specification<Question> spec, Pageable pageable);
	
	@Query("select "
            + "distinct q "
            + "from Question q "
            + "left outer join SiteUser u1 on q.author=u1 "
            + "left outer join Answer a on a.question=q "
            + "left outer join SiteUser u2 on a.author=u2 "
            + "where "
            + "   q.subject like %:kw% "
            + "   or q.content like %:kw% "
            + "   or u1.username like %:kw% "
            + "   or a.content like %:kw% "
            + "   or u2.username like %:kw% ")
	Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);

}
