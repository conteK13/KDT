package com.kosmo.spring_mybatis_demo.basic.ioc.ex02;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // setter, getter, toString... 모두 구성
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 인자 생성자
public class Emp {
    private String name;
    private String dept;
    private int salary;
}
