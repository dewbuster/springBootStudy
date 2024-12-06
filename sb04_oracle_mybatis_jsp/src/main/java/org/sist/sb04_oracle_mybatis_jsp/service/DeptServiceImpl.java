package org.sist.sb04_oracle_mybatis_jsp.service;

import java.util.List;

import org.sist.sb04_oracle_mybatis_jsp.domain.DeptVO;
import org.sist.sb04_oracle_mybatis_jsp.persistence.DeptMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

@Service
public class DeptServiceImpl implements DeptService{
	
	@Resource
	private DeptMapper deptMapper;
	
	@Override
	public List<DeptVO> getDeptList() throws Exception {
		return deptMapper.getDeptList();
	}

}
