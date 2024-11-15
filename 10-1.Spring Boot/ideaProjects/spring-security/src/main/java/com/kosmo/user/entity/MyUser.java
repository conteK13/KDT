package com.kosmo.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

// UserDetails를 상속받아 인증객체로 사용
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@EntityListeners(AuditingEntityListener.class)
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String role;    // 회원의 권한(USER, ADMIN)

    @CreatedDate
    // 생성 날짜를 자동으로 주입 (설정이 필요함
    // => EntityListener
    // @EnableJpaAuditing(SpringDemoApplication에서) 설정 필요)
    @Column(name="create_at", updatable = false)
    private LocalDateTime createAt;     //회원 가입한 날짜

    @LastModifiedDate   // 마지막 수정 날짜를 자동으로 주입(설정 필요)
    @Column(name="updated_at")
    private LocalDateTime updatedAt;     //회원정보 수정한 날짜

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // DB에 저장하는 값은 ADMIN, USER ==?SECURITY에서는 앞에 "ROLE_" 접두어를 붙여 반환해야 함.
        List arr = List.of(new SimpleGrantedAuthority("ROLE_"+this.role));
            //USER => ROLE_USER, ADMIN=> ROLE_ADMIN

        return arr;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override       // id, email 등 unique한 필드를 반환
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;       // => 잠금 되지 않음
        // UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;       // => 잠금 되지 않음
        // UserDetails.super.isAccountNonLocked();
    }

    //패스워드 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;        //만료되지 않음
        //UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return true;        // ==> 사용가능
                // UserDetails.super.isEnabled();
    }
}
