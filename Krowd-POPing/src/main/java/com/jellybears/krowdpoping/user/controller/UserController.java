package com.jellybears.krowdpoping.user.controller;


import com.jellybears.krowdpoping.common.exception.user.UserModifyException;
import com.jellybears.krowdpoping.common.exception.user.UserRegistException;
import com.jellybears.krowdpoping.common.exception.user.UserRemoveException;
import com.jellybears.krowdpoping.common.util.SessionUtil;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import com.jellybears.krowdpoping.user.model.service.EmailService;
import com.jellybears.krowdpoping.user.model.service.UserServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user/*")
@Slf4j
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl UserService;
    private final EmailService emailService;


    public UserController(PasswordEncoder passwordEncoder, UserServiceImpl userService,EmailService emailService) {
        this.passwordEncoder = passwordEncoder;
        this.UserService = userService;
        this.emailService = emailService;

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

    @ResponseBody
    @PostMapping("/emailCheck")
    public String EmailCheck(@RequestBody UserDTO userDTO)
            throws MessagingException, UnsupportedEncodingException {
        String authNum = emailService.sendEmail(userDTO.getEmail());
        return (authNum);
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
    @PostMapping("/idDupCheck")
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

    @GetMapping("/delete")
    public String deleteMember(@ModelAttribute UserDTO user,
                               RedirectAttributes rttr,
                               HttpServletRequest request,
                               HttpServletResponse response) throws UserRemoveException {

        log.info("");
        log.info("");
        log.info("[MemberController] deleteMember ========================================================== start");

        String userId = request.getParameter("id");
        user.setUserId(userId);

        log.info("[UserController] user : " + user);
        UserService.removeUser(user);

        // 회원 탈퇴후 로그아웃 프로세스 진행
        SessionUtil.invalidateSession(request, response);

        rttr.addFlashAttribute("message", "회원 탈퇴에 성공하셨습니다. 로그아웃됩니다.");

        log.info("[MemberController] deleteMember ========================================================== end");

        return "redirect:/krowdpoping/mainpage";
    }

    @GetMapping("/findIdByEmail")
    public String FindId() {
        return "user/findPwd";
    }


    @PostMapping("/findIdByEmail")
    public ResponseEntity<?> findIdByEmail(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");

        try {
            // 서버 로직 수행
            int checkResult = UserService.findIdCheck(email);
            if (checkResult == 0) {
                // 이메일이 존재하지 않을 경우
                return ResponseEntity.status(404).body("이메일을 확인해주세요");
            } else {
                // 이메일이 존재할 경우
                UserDTO user = UserService.findIdByEmail(email);
                return ResponseEntity.ok().body(user);
            }
        } catch (Exception e) {
            // 오류 처리
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/find_Pwd")
    @ResponseBody
    public ResponseEntity<Map<String, String>> find_Pwd(@ModelAttribute UserDTO user, HttpServletResponse response) {
        try {
            UserService.find_pwd(response, user);
            log.info("비밀번호 찾기 성공");

            // JSON 형식의 응답 객체 생성
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "success");
            responseMap.put("redirectUrl", "/user/login");

            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            log.error("비밀번호 찾기 실패", e);

            // 실패 시에도 JSON 형식의 응답 객체 생성
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("message", "failure");

            return ResponseEntity.status(500).body(responseMap);
        }
    }









}


