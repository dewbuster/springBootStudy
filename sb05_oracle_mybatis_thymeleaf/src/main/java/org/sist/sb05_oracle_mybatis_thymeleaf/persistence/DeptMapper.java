package org.sist.sb05_oracle_mybatis_thymeleaf.persistence;

import java.util.List;

import org.sist.sb05_oracle_mybatis_thymeleaf.domain.DeptVO;

// @Mapper
public interface DeptMapper {
	
	List<DeptVO> getDeptList() throws Exception;
   
}