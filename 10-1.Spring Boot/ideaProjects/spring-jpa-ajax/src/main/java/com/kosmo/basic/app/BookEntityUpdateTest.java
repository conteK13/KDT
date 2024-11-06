package com.kosmo.basic.app;

import com.kosmo.basic.entity.BookEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

/*
* 엔티티 수정: 사용자가 엔티티의 속성을 수정하면, JPA는 해당 엔티티가 영속성 컨텍스트에서 관리되고 있는지 확인합니다.
영속성 컨텍스트에 있는 엔티티의 속성을 수정하면, JPA는 이를 "변경 감지(Dirty Checking)"라고 부릅니다.
이 과정에서 JPA는 엔티티의 이전 상태와 현재 상태를 비교하여 어떤 속성이 변경되었는지를 확인합니다.

변경 감지:

JPA는 엔티티가 영속성 컨텍스트에 있을 때, 트랜잭션이 커밋되기 전에 변경된 속성을 자동으로 감지합니다.
이 과정에서 JPA는 엔티티의 현재 상태와 이전 상태를 비교하여 변경된 내용을 확인합니다.
업데이트 실행:

엔티티의 속성이 수정되면 JPA는 변경된 속성을 기반으로 SQL UPDATE 문을 생성하고 이를 데이터베이스에 실행합니다.
이 SQL 문은 수정된 필드만 포함하여 효율적으로 작동합니다.
트랜잭션 커밋:

모든 변경 사항이 영속성 컨텍스트에 저장되면, em.getTransaction().commit()을 호출하여 트랜잭션을 커밋합니다.
이 시점에서 JPA는 데이터베이스에 모든 수정 내용을 반영합니다
* */
public class BookEntityUpdateTest {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("entity_test");
        EntityManager em = factory.createEntityManager();
        Scanner sc = new Scanner(System.in);
        System.out.println("수정할 북 엔티티의 id를 입력하세요 =>");
        Long id = sc.nextLong();
        System.out.println(id+"번 도서를 조회합니다");
        BookEntity b = em.find(BookEntity.class, id);

        System.out.println("*****수정할 도서 정보******");
        System.out.println(b);
        System.out.println("*********Tx 시작*********");

        em.getTransaction().begin();
        if(b!=null){
            sc.nextLine();
            System.out.println("수정할 도서 제목을 입력하세요");
            String newTitle = sc.nextLine();
            b.setTitle(newTitle);

            System.out.println("수정할 도서 가격을 입력하세요");
            int newPrice = sc.nextInt();
            b.setPrice(newPrice);
        }// if---------

        System.out.println("엔터키 입력1 (커밋예정)");
        sc.nextLine();
        sc.nextLine();
        em.getTransaction().commit();   // DB에 반영
        System.out.println("************tx 종료***********");

        em.close();
        factory.close();
    }
}
