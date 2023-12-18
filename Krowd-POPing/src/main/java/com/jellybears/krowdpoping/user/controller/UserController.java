package com.jellybears.krowdpoping.user.controller;

import com.jellybears.krowdpoping.common.exception.user.UserModifyException;
import com.jellybears.krowdpoping.common.exception.user.UserRegistException;
import com.jellybears.krowdpoping.common.util.SessionUtil;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
//import com.jellybears.krowdpoping.user.model.service.EmailServiceImpl;
import com.jellybears.krowdpoping.user.model.service.UserServiceImpl;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Random;

@Controller
@RequestMapping("/user/*")
@Slf4j
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl UserService;

//    private final EmailServiceImpl EmailService;

    public UserController(PasswordEncoder passwordEncoder, UserServiceImpl userService/*, EmailServiceImpl emailService*/) {
        this.passwordEncoder = passwordEncoder;
        this.UserService = userService;
//        this.EmailService = emailService;
    }


    /**
     * 로그인 하기
     */
    @GetMapping("login")
    public String login() {

        return "/user/LoginandSignup";
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

        return "/user/Signup_1";
    }





    /*
     *회원가입 정보 입력 받기
     * */

    @GetMapping("regist")
    public String goRegister() {
        return "/user/Signup_2";
    }


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

//    @PostMapping("/EmailAuth")
//    @ResponseBody
//    public int emailAuth(UserDTO user){
//        log.info("전달받을 이메일 주소 : " + user.getEmail());
//        Random random = new Random();
//        int checkNum = random.nextInt(888888)+111111;
//        String setFrom ="krowdpoping@gmail.com";
//        String toMail = user.getEmail();
//        String title = "Krowd-POPing 회원가입 인증 이메일 ";
//        String content = " 인증 코드는 "+ checkNum+"입니다."+
//                "<br>"+
//                "해당 인증 코드를 인증코드 확인란에 기입하여 주세요.";
//        try{
//            MimeMessage mimemessage = javaMailSender.createMimeMessage();
//        }
//    }




//    @PostMapping("/EmailAuth")
//    @ResponseBody
//    public void emailAuth(UserDTO user)throws Exception{
//        log.info("post emailConfirm");
//        System.out.println("전달 받은 이메일 : "+ user.getEmail());
//        EmailService.sendSimpleMessage(user.getEmail());
//    }
//    @PostMapping("/verifyCode")
//    @ResponseBody
//    public int verifyCode(String code) {
//        log.info("Post verifyCode");
//
//        int result = 0;
//        System.out.println("code : "+code);
//        System.out.println("code match : "+ EmailServiceImpl.ePw.equals(code));
//        if(EmailServiceImpl.ePw.equals(code)) {
//            result =1;
//        }
//
//        return result;
//    }



    @PostMapping("idDupCheck")
    public ResponseEntity<String> checkDuplication(@RequestBody UserDTO userDTO) {
        log.info("");
        log.info("");
        log.info("[UserController} checkDuplication===============================");

        String result = "사용 가능한 아이디 입니다.";
        log.info("[UserController} Request Check Id : " + userDTO.getUserId());

        if ("".equals(userDTO.getUserId())) {
            log.info("[UserController} No Input User ID");
            result = "아이디를 입력해 주세요 ";
        } else if (UserService.selectUserById(userDTO.getUserId())) {
            log.info("[UserController} Already Exist");
            result = "중복된 아이디가 존재합니다";
        }
        log.info("[UserController] checkDuplication =======================");
        return ResponseEntity.ok(result);
    }


    @GetMapping("signupsuccess")
    public String SignupSuccess() {
        return "user/Signup_3";
    }

    @GetMapping("update")
    public String editProfile() {
        log.info("editProfile method called");

        return "mypage/mypage_editprofile.html";
    }
    @PostMapping("update")
    public String modifyUser(@ModelAttribute UserDTO user,
                             HttpServletRequest request,
                             HttpServletResponse response,
                             RedirectAttributes rttr) throws UserModifyException {

        log.info("");
        log.info("");
        log.info("[UserController] modifyUser ========================================================== start");

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        log.info("[UserController] modifyUser request User : " + user);

        UserService.modifyUser(user);

        // 회원정보 수정후 로그아웃 프로세스 진행
        SessionUtil.invalidateSession(request, response);

        rttr.addFlashAttribute("message", "회원 정보 수정에 성공하셨습니다. 다시 로그인해주세요.");

        log.info("[UserController] modifyUser========================================================== end");

        return "redirect:/user/login";
    }


}


