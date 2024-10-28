package com.kosmo.spring_mybatis_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
		//(exclude = DataSourceAutoConfiguration.class)
public class SpringMybatisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMybatisDemoApplication.class, args);
	}

}
