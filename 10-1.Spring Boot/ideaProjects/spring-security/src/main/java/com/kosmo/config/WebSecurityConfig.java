package com.kosmo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailService;

    private static final String[] WHITE_LIST
            ={"/", "/login", "/signup", "/public/**"};
    @Bean
    public WebSecurityCustomizer configure(){
        return (web-> web.ignoring()        // 보안 필터 체인을 거치지 않을 url 패턴 지정
                .requestMatchers("/static/**")
        );
    }//------------------------------

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.requestMatchers(WHITE_LIST).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // 관리자 전용 경로 설정
                        //.requestMatchers("/auth/**").hasRole("USER")   // 일반 회원 전용 경로 설정
                        .requestMatchers("/auth/**").hasAnyRole("USER", "ADMIN")    // 일반 회원 전용 경로 설정
                        .anyRequest().authenticated()       // 위 설정한 WHITH_LIST 외에는 인증이 필요함
                ).formLogin(form -> form.loginPage("/login")
                        .failureForwardUrl("/")
                        .defaultSuccessUrl("/")
                )
                .logout(logout -> logout.logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                )
                .exceptionHandling(exception->exception.accessDeniedPage("/accessDenied"))
                .csrf(csrf -> csrf.disable()); //csrf 비활성화(개발자), 운영시에는 활성화

        return http.build();
    }//---------------------

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //인증 관리자 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder passwordEncoder)
    throws Exception
    {
        DaoAuthenticationProvider authProvider= new DaoAuthenticationProvider();
        // DaoAuthenticationProvider는 UserDetailsService를 사용하여
        // 사용자 정보를 db 에서 가져와 인증 처리하는 객체.
        authProvider.setUserDetailsService(userDetailService);
        // UserDetailService 는 사용자 정보를 가져오는 역할을 하는 객체
        // 이메일로 사용자 정보를 DB 에서 조회하는 로직을 처리하고
        // AuthenticationManager 는 이를 바탕으로 인증을 진행한다.
        // 만약 회원이 아니라면 AuthenticationManager 는 AuthenticationException
        // 을 발생시킨다 ==? 예외는 ExceptionTranslationFilter 에서
        // 처리되어 로그인 실패 시 리다이렉트시키거나 메시지를 발생시킨다.
        authProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authProvider);
    }



}////////////////////////////////////////
