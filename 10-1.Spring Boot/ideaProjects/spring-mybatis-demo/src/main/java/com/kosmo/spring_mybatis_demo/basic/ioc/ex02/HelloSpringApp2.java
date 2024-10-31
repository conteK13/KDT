package com.kosmo.spring_mybatis_demo.basic.ioc.ex02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloSpringApp2 {
    public static void main(String[] args){
        // 스프링 컨테이너 ApplicationContext
        // xml 설정 ==> FileSystemXmlApplicationContext
        // Java 설정 ==> AnnotationConfigApplicationContext

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Emp e1 = ctx.getBean("e1", Emp.class);
        System.out.println("e1 : "+e1);

        Emp e2 = ctx.getBean("empInfo2", Emp.class);
        System.out.println("e2 : "+e2);

        // ServiceImpl 객체를 lookup해서 info() 메서드를 호출하세요.
        Service svc = ctx.getBean("service", Service.class);
        svc.info();
    }
}
