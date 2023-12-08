package com.jellybears.krowdpoping.user.controller;

import com.jellybears.krowdpoping.user.model.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/loginandsignup/*")
@Slf4j
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl UserService;

    public UserController(PasswordEncoder passwordEncoder, UserServiceImpl userService) {
        this.passwordEncoder = passwordEncoder;
        this.UserService = userService;
    }

    /**
         * 로그인 하기
         */
        @GetMapping("login")
        public String login() {

            return "/loginandsignup/LoginandSignup.html";
        }

        /**
         * 회원가입 이용약관 조회 페이지
         */
        @GetMapping("termofservice")
        public String TermOfService() {

            return "/loginandsignup/Signup_1.html";
        }

        @GetMapping("entermemberinfo")
        public String EnterMemberInfo(){
            return"/loginandsignup/Signup_2.html";
        }

    }

