package org.sist.sb04_oracle_mybatis_jsp.persistence;

import java.util.List;

import org.sist.sb04_oracle_mybatis_jsp.domain.DeptVO;

// @Mapper
public interface DeptMapper {
	
	List<DeptVO> getDeptList() throws Exception;
   
}