package com.kosmo.spring_mybatis_demo.basic.ioc.ex03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloSpringApp3 {
    public static void main(String[] args) {
        ApplicationContext ctx
                = new AnnotationConfigApplicationContext(MyConfig.class);
        Service svc = ctx.getBean("svc", Service.class);
        svc.info1();    // member정보
        svc.info2();    // dao 정보
    }
}
