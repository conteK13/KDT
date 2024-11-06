package com.kosmo.basic.app;

import com.kosmo.basic.entity.BookEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.hibernate.annotations.processing.SQL;

import java.util.Scanner;
/*
영속성 컨텍스트에서의 삭제: remove() 메서드는 지정된 엔티티를 영속성 컨텍스트에서 제거합니다.
이는 해당 엔티티가 더 이상 관리되지 않음을 의미합니다. 즉, JPA가 이 엔티티에 대한 변경 사항을 추적하지 않게 됩니다.
트랜잭션의 중요성: remove() 메서드 호출 후 트랜잭션이 커밋되기 전까지는 실제로 데이터베이스에서 삭제되지 않습니다.
따라서, 트랜잭션이 커밋되면 삭제가 데이터베이스에 반영됩니다. 반대로, 트랜잭션이 롤백되면 삭제 작업이 취소되고,
엔티티는 여전히 영속성 컨텍스트에서 관리됩니다.
캐시와 플러시: remove()를 호출하면 엔티티가 영속성 컨텍스트에서 제거되지만,
실제 삭제 작업은 트랜잭션 커밋 시점에 데이터베이스에 반영됩니다.
영속성 컨텍스트는 엔티티의 상태를 캐시로 관리하고 있기 때문에,
flush() 메서드를 호출할 필요는 없지만, flush()를 호출하면
즉시 데이터베이스에 SQL DELETE 쿼리가 실행됩니다.
*/


public class BookEntityDeleteTest {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("entity_test");
        EntityManager em = factory.createEntityManager();

        TypedQuery<BookEntity> query= em.createQuery("select b from BookEntity b", BookEntity.class);
        query.getResultList().stream().forEach(System.out::println);

        Scanner sc = new Scanner(System.in);
        System.out.println("엔터키1...");
        sc.nextLine();
        System.out.println("엔티티 삭제 예정");

        em.getTransaction().begin();
        System.out.println("********Tx 시작됨*********");

        // 조회
        BookEntity findBook = em.find(BookEntity.class, 4L);
        System.out.println("4번 도서 : "+ findBook);
        if(findBook!=null){
            // 삭제
            em.remove(findBook);
            System.out.println("remove() 호출함");
        }
        System.out.println("엔터키2...");
        sc.nextLine();
        query= em.createQuery("select b from BookEntity b", BookEntity.class);
        query.getResultList().stream().forEach(System.out::println);

        System.out.println("엔터키3...(커밋 예정)");
        sc.nextLine();
        em.getTransaction().commit();   //실제 DB에 삭제 반영
        System.out.println("******Tx종료됨****");
        query = em.createQuery("select b from BookEntity b", BookEntity.class);
        query.getResultList().stream().forEach(System.out::println);
        em.close();
        factory.close();
        sc.close();

    }
}
