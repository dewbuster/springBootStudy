package org.sist.sb02_mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.sist.sb02_mybatis.persistence")
public class Sb02MybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb02MybatisApplication.class, args);
	}

}
