package org.sist.sb05_oracle_mybatis_thymeleaf;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.sist.sb05_oracle_mybatis_thymeleaf.persistence.TimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.java.Log;

@SpringBootTest
@Log
class Sb05OracleMybatisThymeleafApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	TimeMapper timeMapper;
	
	@Test
	void timeMapperTest() {
		System.out.println("> 현재 시간: " + this.timeMapper.getTimeXML());
	}
	
	@Autowired
	DataSource dataSource;
	
	@Test
	void testConnection() {
		try (Connection con = this.dataSource.getConnection()) {
			
			log.info(">Connection: " + con);
			// >Connection: HikariProxyConnection@380146513 wrapping oracle.jdbc.driver.T4CConnection@d1c5cf2
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
