package com.kosmo.basic.app;

import com.kosmo.basic.entity.BookEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;

public class BookEntityInsertTest2 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("entity_test");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        BookEntity b= null;
        for(int i=6; i<9; i++){
            b=new BookEntity(null, "jax 기초 "+i, "길벗 출판사", 25000, "a.jpg");
            em.persist(b);
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("엔터키 1....");
        sc.nextLine();
        TypedQuery<BookEntity> query = em.createQuery("select b from BookEntity b", BookEntity.class);
        List<BookEntity> list = query.getResultList();

        list.stream().forEach(System.out::println);
        System.out.println("엔터키 2....(em.flush()할 예정임...)");
        sc.nextLine();
        em.flush();     // flush()를 호출하면 영속성 컨텍스트에 있는 모든 변경사항이 DB에 반영된다.
//        em.getTransaction().rollback();
        em.getTransaction().commit();
        // rollback()==> flush()를 했어도 트랜젝션 내에 문제가 발생하거나 취소되면 변경사항이 취소된다.
//        System.out.println("***Tx 롤백됨***************");
        System.out.println("***Tx 커밋됨***************");
        sc.close();
        em.close();
        factory.close();
    }
}
