package com.kosmo.book.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "membertbl")
// 연관관계 시는 toString은 오버라이드 하지 않는 것이 좋다.
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<Review> reviews = new ArrayList<>();
    // cascade = CascadeType.ALL : 엔티티간의 연관관계에서 영속성 전이를 활성화
    // ==> 특정 엔티티에 수행된 영속성 작업(저장, 삭제 등) 이 연관된 엔티티에도
    // 동일하게 적용하겠다는 의미


}
