package com.kosmo.book.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="usertbl")
@Getter
@Setter
@ToString(of={"id", "name", "userid"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@NamedQuery(name = "User.findByUserid", query = "select u from User u where u.userid = :userid")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String userid;

}
