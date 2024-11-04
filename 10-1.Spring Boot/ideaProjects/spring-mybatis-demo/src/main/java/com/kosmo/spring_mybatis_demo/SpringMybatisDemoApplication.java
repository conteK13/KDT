package com.kosmo.spring_mybatis_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
		//(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.kosmo.user.controller", "com.kosmo"})
@MapperScan(basePackages = {"com.kosmo.user.mapper", "com.kosmo.board.mapper"})
public class SpringMybatisDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringMybatisDemoApplication.class, args);
	}

}
