package com.kosmo.spring_mybatis_demo.basic.ioc.ex02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ServiceImpl implements Service{

    @Autowired  // byType으로 주입한다. Emp 타입의 객체가 있으면 주입.
    @Qualifier("empInfo2")    // 동일한 타입의 객체가 여러개 있을 때는 @Qualifier("빈이름")
                        // 어노테이션을 기술하여 어떠 객체인지 명시해야 함
    private Emp emp;    // Emp 타입의 객체가 2개 있음(e1, empInfo2)
    // ServiceImple이 Emp에 의존한다.(use)

    @Override
    public void info(){
        System.out.printf(" Name: %s%n Dept: %s%n Salary : %d%n",
                emp.getName(), emp.getDept(), emp.getSalary());
    }
}
