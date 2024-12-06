package org.sist.sb04_oracle_mybatis_jsp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_sample")
public class Sample {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(generator = "seq_tablsampl", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_tablsampl", sequenceName = "seq_tablsampl", initialValue = 1, allocationSize = 1)
	private Long sno;
	private String col1;
	private String col2;
	
}

/*
Hibernate: create table tbl_sample (sno number(19,0) not null, col1 varchar2(255 char), col2 varchar2(255 char), primary key (sno))
Hibernate: create sequence seq_tablsampl start with 1 increment by 1

*/