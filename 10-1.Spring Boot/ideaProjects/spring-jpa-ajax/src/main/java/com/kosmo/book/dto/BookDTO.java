package com.kosmo.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
 * DTO(Data Transfer Object)와 Entity를 구분해서 작성하는 이유
 * - 영속성 계층과 표현 계층의 역할 분리
 *    Entity : DB와 상호작용을 위한 객체. 테이블과 매핑되며 jpa는 이를 가반으로 DB에 CRUD수행
 *             외부에 노출되면 안되는 민감한 데이터(암호화된 비번) 를 포함할 수 있다
 *               =>DTO에서는 필요한 데이터만 선택적으로 클라이언트에게 제공
 *             엔티티는 DB스키마와 일치해야 하는 경우가 많으나 DTO는 클라이언트가 필요로하는 형식에 맞춰
 *             데이터를 가공하여 전달할 수 있다
 *    DTO : 클라이언트와 데이터 교환에 초점을 맞춘 객체. 전달된 정보를 표현하는데 사용.
 *           주로 Controller와 Service계층 간 데이터 전달에 사용되며, API의 응답형식을 지정하는데 유용함
 * */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter     //클라이언트로 넘어가는 정보를 제한하기 위해 ToString, Entity X
public class BookDTO {
    private Long id;
    private String title;
    private String publish;
    private int price;
    private String BookImage;
}
