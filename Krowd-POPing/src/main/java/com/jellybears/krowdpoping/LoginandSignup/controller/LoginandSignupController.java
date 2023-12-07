package com.jellybears.krowdpoping.LoginandSignup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/loginandsignup/*")
public class LoginandSignupController {

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
