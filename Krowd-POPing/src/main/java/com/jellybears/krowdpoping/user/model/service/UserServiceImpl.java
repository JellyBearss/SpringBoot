package com.jellybears.krowdpoping.user.model.service;


import com.jellybears.krowdpoping.common.exception.user.UserModifyException;
import com.jellybears.krowdpoping.common.exception.user.UserRegistException;

import com.jellybears.krowdpoping.common.exception.user.UserRemoveException;
import com.jellybears.krowdpoping.user.model.dao.UserMapper;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.util.Random;

//회원 정보 수정 추가 예정
//회원 삭제 추가 예정
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    private final JavaMailSender emailSender;


    public UserServiceImpl(PasswordEncoder passwordEncoder, UserMapper mapper, EmailService emailService, JavaMailSender emailSender) {
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.emailSender = emailSender;
    }


    /*-------------회원 조회-----------*/
    @Override
    public boolean selectUserById(String userId) {
        UserDTO result = mapper.selectUserById(userId);
        return result != null ? true : false;
    }


    /*-----------회원 가입------------------*/
    @Override
    @Transactional
    public void registUser(UserDTO user) throws UserRegistException {

        log.info("[UserService] Insert User : " + user);

        int result = mapper.insertUser(user);

        int userCode = mapper.selectLastInsertUserCode();

        System.out.println(userCode + "===================user_code===============");
        log.info(String.valueOf(result));
        log.info(String.valueOf(userCode));

        int result1 = mapper.insertRoletype(userCode);


        System.out.println("result1.getUserId()========== : ");


        log.info("[UserService] Insert User : " + ((result > 0) ? "회원가입 성공" : "회원가입 실패"));

        if (!(result > 0)) {
            throw new UserRegistException("회원 가입에 실패하였습니다.");
        }
    }


    /*-------------회원 정보 수정------------*/

    @Override
    public void modifyUser(UserDTO user) throws UserModifyException {
        int result = mapper.updateUser(user);

        if (!(result > 0)) {
            throw new UserModifyException("회원 정보 수정에 실패하셨습니다.");
        }
    }


    /*-------------회원 탈퇴------------*/
    @Override
    public void removeUser(UserDTO user) throws UserRemoveException {
        int result = mapper.deleteUser(user);

        if (!(result > 0)) {
            throw new UserRemoveException("회원 탈퇴에 실패하셨습니다.");
        }
    }

    @Override
    public UserDTO findIdByEmail(String email) throws Exception {
        return mapper.findIdByEmail(email);

    }

    @Override
    public int findIdCheck(String email) throws Exception {
        return mapper.findIdCheck(email);
    }


    @Override
    public String send_PwdMail(UserDTO user) throws Exception {
        try {
            String toEmail = user.getEmail(); // 사용자 객체에서 이메일 주소 가져오기

            // 인증 코드 생성
            String Pwdcode = createCode();

            // 이메일 보내기
            sendEmail(toEmail, Pwdcode);

            // 인증 코드를 데이터베이스에 저장하거나 필요한 대로 사용할 수 있습니다
            // 지금은 단순히 출력합니다
            log.info("전송된 인증 코드: " + Pwdcode);
            return Pwdcode;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("비밀번호 재설정 이메일 전송에 실패했습니다.", e);
        }
    }

    // 인증 코드 생성
    private String createCode() {
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0:
                    key.append((char) ((int) random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) ((int) random.nextInt(26) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(9));
                    break;
            }
        }
        return key.toString();
    }

    // 이메일 보내기
    private void sendEmail(String toEmail, String Pwdcode) throws MessagingException, UnsupportedEncodingException {
        try {
            MimeMessage emailForm = createEmailForm(toEmail, Pwdcode);
            emailSender.send(emailForm);
        } catch (MessagingException | UnsupportedEncodingException e) {
            // 이메일 전송 실패 시 로그에 기록
            // 실패한 이유에 대한 로그를 남길 수 있습니다.
            e.printStackTrace(); // 또는 원하는 형태로 로깅
            throw new MessagingException("이메일 전송에 실패하였습니다.", e);
        }
    }

    // 이메일 양식 작성
    private MimeMessage createEmailForm(String email, String authCode) throws MessagingException, UnsupportedEncodingException {
        String setFrom = "krowdpoping@gmail.com"; // 이메일 설정에 있는 자신의 이메일 주소(보내는 사람)
        String toEmail = email; // 받는 사람
        String title = "KrowdPOPing 임시 비밀번호"; // 제목

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email); // 보낼 이메일 설정
        message.setSubject(title); // 제목 설정
        message.setFrom(setFrom); // 보내는 이메일
        message.setText(setContext(authCode), "utf-8", "html");

        return message;
    }

    // 이메일 내용 설정
    private String setContext(String Pwdcode) {
        Context context = new Context();
        String emailContent =
                "<h1>안녕하세요.</h1>\n" +
                        "<h1>KrowdPoping 입니다.</h1>\n" +
                        "<br>\n" +
                        "<p>회원님의 임시 비밀번호 입니다:</p>\n" +
                        "<p style=\"font-size:130%;\">" + Pwdcode + "</p>\n" +
                        "<p>로그인 후 비밀번호를 변경해주세요.</p>\n";

        context.setVariable("code", Pwdcode);
        return emailContent;
    }


    @Override
    public void find_pwd(HttpServletResponse response, UserDTO user) throws Exception {
        try {
            // 사용자에게 임시 비밀번호를 전송 및 임시비번 담음
            String generatedPwd = send_PwdMail(user);
            user.setPassword(generatedPwd);


            // 비밀번호를 암호화하여 저장
            String encPwd = passwordEncoder.encode(generatedPwd);
            user.setPassword(encPwd);

            // 비밀번호 변경
            mapper.FindUpdatePwd(user);


        } catch (Exception e) {

            e.printStackTrace();
        }
    }



}

























