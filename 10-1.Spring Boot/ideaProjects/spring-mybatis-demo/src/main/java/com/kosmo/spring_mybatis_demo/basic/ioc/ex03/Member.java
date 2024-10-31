package com.kosmo.spring_mybatis_demo.basic.ioc.ex03;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@ToString   //toString()을 재정의함
public class Member {

    @Value("홍길동")
    private String name;

    @Value("010-2222-3333")
    private String tel;




}
