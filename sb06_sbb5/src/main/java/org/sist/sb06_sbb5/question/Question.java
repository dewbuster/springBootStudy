package org.sist.sb06_sbb5.question;

import java.time.LocalDateTime;
import java.util.List;

import org.sist.sb06_sbb5.answer.Answer;
import org.sist.sb06_sbb5.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 200)
	private String subject;
	
	// 글자수 제한이 없는 긴 문자열
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Answer> answerList;
	
	@ManyToOne
	private SiteUser author;

}
