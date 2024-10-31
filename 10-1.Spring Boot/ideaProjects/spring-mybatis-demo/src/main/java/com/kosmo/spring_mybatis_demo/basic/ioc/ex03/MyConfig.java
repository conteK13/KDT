package com.kosmo.spring_mybatis_demo.basic.ioc.ex03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public Member getMember(){
        return new Member();
    }

    @Bean
    public OracleDao oracle(){
        return new OracleDao();
    }

    @Bean
    public MySQLDao mysql(){
        return new MySQLDao();
    }

    @Bean(name="svc")
    public ServiceImpl service(){
        return new ServiceImpl();
    }
}
