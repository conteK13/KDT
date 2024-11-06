package com.kosmo.basic.app;
import com.kosmo.basic.entity.BookEntity;
import jakarta.persistence.*;

import java.util.Scanner;

//순수 JPA : EntityManager가 CRUD 기능을 다 가지고 있음.
public class BookEntityInsertTest {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("entity_test");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();        // 트랜젝션 시작
        System.out.println("*****트랜젝션 시작*****");
        BookEntity book = null;
        for (int i=1; i<6; i++){
            book=new BookEntity();
            book.setTitle("스프링부트3 JPA 기초 " + i);
            book.setPublish("골든 래빗 출판사");
            book.setPrice(20000);
            book.setBookImage("book"+i+".jpg");
            em.persist(book);
        }// for---------
        System.out.println("엔터키를 입력하세요");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.close();

        em.getTransaction().commit();
        System.out.println("*****트랜젝션 종료*****");
        em.close();
        factory.close();

    }
}
