package com.kosmo.basic.app;


import com.kosmo.basic.entity.BookEntity;
import jakarta.persistence.*;

import java.util.List;

public class BookEntitySelectTest {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("entity_test");
        EntityManager em = factory.createEntityManager();

        // 조회[1] em.find()
        BookEntity findBook = em.find(BookEntity.class, 5L);
        System.out.println(findBook.getTitle()+", "+findBook.getPrice());

        // 조회[2] JPQL을 이용한 조회==> createQuery(JPQL)
        System.out.println("***********모든 도서 조회*********");
        // JPQL : Java Persistence Query Language   -> 엔티티에서 모든 레코드를 선택하는 쿼리
        TypedQuery<BookEntity>  query = em.createQuery("select b from BookEntity b", BookEntity.class);
        List<BookEntity> list = query.getResultList();
        for(BookEntity entity : list){
            System.out.println(entity);
        }
        System.out.println("---------------");

        list.stream().forEach(System.out::println);

        em.close();
        factory.close();
    }
}
