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
        http// 페이지 권한 설정
                .authorizeHttpRequests(auth ->{
                        // auth.requestMatchers("/notice/*", "/board/*", "/thumbnail/**").hasAnyAuthority("ROLE_MEMBER", "ROLE_ADMIN");
                        // auth.requestMatchers("/notice/regist").hasAnyAuthority("ROLE_ADMIN");
                        // auth.requestMatchers("/*", "/member/*").permitAll();// 모든 리소스를 권한 없이 사용가능
                        auth.anyRequest().permitAll();
                        // auth.anyRequest().authenticated();
                })

                .csrf(csrf -> csrf.disable());

        return http.build();



        }



}
