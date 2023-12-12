package com.jellybears.krowdpoping.user.controller;

import com.jellybears.krowdpoping.common.exception.member.UserRegistException;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import com.jellybears.krowdpoping.user.model.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        return "/loginandsignup/LoginandSignup";
    }

    /**
     * 회원가입 이용약관 조회 페이지
     */
    @GetMapping("termofservice")
    public String TermOfService() {

        return "/loginandsignup/Signup_1";
    }
/*회원가입 정보 입력 받기*/
    @PostMapping("entermemberinfo")
    public String EnterMemberInfo(@ModelAttribute UserDTO user,
                                   @RequestParam String emailAddress,
                                   @RequestParam String at,
                                   @RequestParam String emailProvider,
                                   RedirectAttributes rttr)throws UserRegistException {

        log.info("");
        log.info("");
        log.info("[UserController] registUser start========================================");

        String email = emailAddress+at+emailProvider;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail().replace("직접입력", ""));
        user.setPhone_number(user.getPhone_number().replace("-", ""));
        user.setEmail(email);

        log.info("[UserController] registUser request User : " + user);
        UserService.registUser(user);

        rttr.addFlashAttribute("message", "회원 가입에 성공하였습니다.");

        return "/loginandsignup/Signup_2";
    }

    @GetMapping("signupsuccess")
    public String SignupSuccess() {
        return "/loginandsignup/Signup_3";
    }
}

