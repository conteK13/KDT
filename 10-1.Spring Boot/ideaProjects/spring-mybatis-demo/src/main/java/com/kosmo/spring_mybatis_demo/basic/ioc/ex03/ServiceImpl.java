package com.kosmo.spring_mybatis_demo.basic.ioc.ex03;


import jakarta.annotation.Resource;
import jakarta.inject.Inject;

public class ServiceImpl implements Service {

    @Inject //byType으로 주입
    private Member user;

    @Resource(name="mysql") //byName으로 주입.
    private Dao dao;


    @Override
    public void info1(){
        System.out.println("----Member Info ----");
        System.out.println(user.toString());
    }

    @Override
    public void info2() {
        System.out.println(dao.daoInfo());

    }
}
