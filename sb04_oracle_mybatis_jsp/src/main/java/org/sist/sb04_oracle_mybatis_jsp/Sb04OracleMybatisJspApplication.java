package org.sist.sb04_oracle_mybatis_jsp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.sist.sb04_oracle_mybatis_jsp.persistence")
public class Sb04OracleMybatisJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb04OracleMybatisJspApplication.class, args);
	}

}
