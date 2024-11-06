package com.kosmo.basic.app;
import com.kosmo.basic.entity.BookEntity;
import jakarta.persistence.*;

public class BookEntityTest {
    public static void main(String[] args) {
        //src/main/resources/META-INF/persistence.xml 파일에 정의된
        //persistence unit 이름을 넣어준다
        EntityManagerFactory factory
                =Persistence.createEntityManagerFactory(
                "entity_test");
        System.out.println("EntityManagerFactory객체: "
                +factory.getClass().getName());
        EntityManager em=factory.createEntityManager();
        System.out.println("EntityManger객체: "+em.getClass().getName());

        em.getTransaction().begin();
        System.out.println("***트랜잭션 시작************");
        BookEntity b=new BookEntity(null,"JPA기초",
                "한빛",30000,"b1.jpg");
        em.persist(b);// C -insert 작업을 함
        em.getTransaction().commit();//트랜잭션 종료
        System.out.println("***트랜잭션 종료************");
        em.close();
        factory.close();



    }
}
