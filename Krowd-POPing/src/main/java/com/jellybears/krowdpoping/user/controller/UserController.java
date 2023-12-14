package com.jellybears.krowdpoping.user.controller;

import com.jellybears.krowdpoping.common.exception.user.UserModifyException;
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
@RequestMapping("/user/*")
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

        return "/user/LoginandSignup";
    }

    /**
     * 회원가입 이용약관 조회 페이지
     */
    @GetMapping("termofservice")
    public String TermOfService() {

        return "/user/Signup_1";
    }





    /*
    *회원가입 정보 입력 받기
    * */

    @GetMapping("regist")
    public String goRegister(){return "/user/Signup_2";}


    @PostMapping("/regist")
    public String registUser(@ModelAttribute UserDTO user,
                               RedirectAttributes rttr) throws UserRegistException {

        log.info("");
        log.info("");
        log.info("[UserController] registUser ========================================================== start");

        user.setPhone_number(user.getPhone_number().replace("-", ""));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        log.info("[UserController] registUser request User : " + user);

        //UserService.registUser(user);

        rttr.addFlashAttribute("message", "회원 가입에 성공하였습니다.");

        log.info("[UserController] registUser ========================================================== end");

        UserService.registUser(user);

        return "redirect:/user/signupsuccess";
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
        log.info("[UserController] checkDuplication =======================");
        return  ResponseEntity.ok(result);
    }

//    @PostMapping("update")
//    public String modifyUser(@ModelAttribute UserDTO user
//                             RedirectAttributes rttr) throws UserModifyException {




    @GetMapping("signupsuccess")
    public String SignupSuccess() {
        return "/user/Signup_3";
    }
}

