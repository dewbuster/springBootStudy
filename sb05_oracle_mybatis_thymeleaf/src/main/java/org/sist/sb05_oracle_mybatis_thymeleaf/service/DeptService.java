package org.sist.sb05_oracle_mybatis_thymeleaf.service;

import java.util.List;

import org.sist.sb05_oracle_mybatis_thymeleaf.domain.DeptVO;

public interface DeptService {
	
	List<DeptVO> getDeptList() throws Exception;
	
}
