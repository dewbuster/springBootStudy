package org.sist.sb06_sbb7.answer;

import java.time.LocalDateTime;
import java.util.Set;

import org.sist.sb06_sbb7.question.Question;
import org.sist.sb06_sbb7.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(columnDefinition = "TEXT")
	private String content;
	private LocalDateTime createDate;
	
	@ManyToOne
	private Question question; // 외래키  주의
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate;
	
	@ManyToMany
	private Set<SiteUser> voter;
	// 중복 투표 막기 위해 Set 사용
}
