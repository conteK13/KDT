package com.kosmo.spring_mybatis_demo.basic.ioc.ex03;

public class MySQLDao implements Dao{
    public String daoInfo(){
        return "나는 MySQL DB와 연동해요~";
    }
}
