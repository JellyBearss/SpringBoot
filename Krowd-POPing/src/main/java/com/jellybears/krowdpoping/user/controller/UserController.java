package com.jellybears.krowdpoping.user.controller;

import com.jellybears.krowdpoping.common.exception.user.UserRegistException;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import com.jellybears.krowdpoping.user.model.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("loginfail")
    public String goLoginFail() {

        return "errors/loginerror";
    }

    /**
     * 회원가입 이용약관 조회 페이지
     */
    @GetMapping("termofservice")
    public String TermOfService() {

        return "/loginandsignup/Signup_1";
    }





    /*
    *회원가입 정보 입력 받기
    * */

    @GetMapping("entermemberinfo")
    public String goRegister(){return "/loginandsignup/Signup_2";}


    @PostMapping("entermemberinfo")
    public String registUser(@ModelAttribute
                                 UserDTO userDTO,
                                 RedirectAttributes rttr)throws UserRegistException {


        log.info("");
        log.info("");
        log.info("[UserController] registUser start========================================");


        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setPhone_number(userDTO.getPhone_number().replace("-", ""));
//        String email = emailAddress+at+emailProvider;
//        user.setEmail(user.getEmail().replace("직접입력", ""));
//        user.setEmail(email);

        log.info("[UserController] registUser request User : " + userDTO);
        UserService.registUser(userDTO);

        rttr.addFlashAttribute("message", "회원 가입에 성공하였습니다.");
        log.info("[MemberController] registMember ========================================================== end");

        return "/loginandsignup/Signup_3";
    }

    @PostMapping("idDupCheck")
    public ResponseEntity<String> checkDuplication(@RequestBody UserDTO userDTO){
        log.info("");
        log.info("");
        log.info("[UserController} checkDuplication===============================");

        String result ="사용 가능한 아이디 입니다.";
        log.info("[UserController} Request Check Id : " + userDTO.getUserId());

        if("".equals(userDTO.getUserId())){
            log.info("[UserController} No Input User ID");
            result = "아이디를 입력해 주세요 ";
        } else if(UserService.selectUserById(userDTO.getUserId())){
            log.info("[UserController} Already Exist");
            result = "중복된 아이디가 존재합니다";
        }
        log.info("[UserController] checkDupication =======================");
        return  ResponseEntity.ok(result);
    }

    @GetMapping("signupsuccess")
    public String SignupSuccess() {
        return "/loginandsignup/Signup_3";
    }
}

