package com.jellybears.krowdpoping.config;

import com.jellybears.krowdpoping.config.handler.AuthFailHandler;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthFailHandler authFailHandler;

    public SecurityConfig(AuthFailHandler authFailHandler) {
        this.authFailHandler = authFailHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 정적 리소스 설정 제외 처리
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());


    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                // 로그인 설정
                .formLogin(login -> {
                    login.loginPage("/user/login");   //커스텀 로그인 페이지 사용
                    login.usernameParameter("userId"); // 사용자 id 입력 필드 (input의 name과 일치)
                    login.passwordParameter("password"); // 사용자 pass 입력 필드 (input의 name과 일치)
                    login.defaultSuccessUrl("/krowdpoping/mainpage");  //로그인 성공시 이동 페이지
                    login.failureHandler(authFailHandler); // auth
                })

                //로그아웃 설정
                .logout(logout ->{
                    logout.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")); // 로그아웃 요청 url
                    logout.deleteCookies("JSESSIONID"); // 로그아웃 시 사용자의 JSESSIONID 삭제
                    logout.invalidateHttpSession(true); // 서버 세션 소멸처리
                    logout.logoutSuccessUrl("/krowdpoping/mainpage"); // 로그아웃 성공시 이동할 페이지
                })


                .sessionManagement(session ->{
                    session.maximumSessions(1); // 세션 개수 제한
                    session.invalidSessionUrl("/"); // 세션 만료시 이동할 url
                })
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

}// 페이지 권한 설정
//                .authorizeHttpRequests(auth ->{
//                    auth.requestMatchers("/*").permitAll();// 모든 리소스를 권한 없이 사용가능
//                    auth.anyRequest().authenticated();
//                })




