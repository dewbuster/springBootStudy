package org.sist.sb04_oracle_mybatis_jsp.service;

import java.util.List;

import org.sist.sb04_oracle_mybatis_jsp.domain.DeptVO;

public interface DeptService {
	
	List<DeptVO> getDeptList() throws Exception;
	
}
