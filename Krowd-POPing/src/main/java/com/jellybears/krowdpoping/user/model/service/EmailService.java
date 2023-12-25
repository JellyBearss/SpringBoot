package com.jellybears.krowdpoping.user.model.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.thymeleaf.context.Context;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender; //의존성 주입을 통해서 필요한 객체를 가져옴
    private String authNum; //랜덤 인증 코드

    //랜덤 인증 코드 생성
    public void createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for(int i=0;i<8;i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0 :
                    key.append((char) ((int)random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) ((int)random.nextInt(26) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(9));
                    break;
            }
        }
        authNum = key.toString();
    }

    //메일 양식 작성
    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {

        createCode(); //인증 코드 생성
        String setFrom = "krowdpoping@gmail.com"; //email-config에 설정한 자신의 이메일 주소(보내는 사람)
        String toEmail = email; //받는 사람
        String title = "KrowdPOPing 회원가입 이메일 인증 번호"; //제목


        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email); //보낼 이메일 설정
        message.setSubject(title); //제목 설정
        message.setFrom(setFrom); //보내는 이메일
        message.setText(setContext(authNum), "utf-8", "html");

        return message;
    }


    //실제 메일 전송
    public String sendEmail(String toEmail) throws MessagingException, UnsupportedEncodingException {
        try {
            MimeMessage emailForm = createEmailForm(toEmail);
            emailSender.send(emailForm);
            return authNum;
        } catch (MessagingException | UnsupportedEncodingException e) {
            // 이메일 전송 실패 시 로그에 기록
            // 실패한 이유에 대한 로그를 남길 수 있습니다.
            e.printStackTrace(); // 또는 원하는 형태로 로깅
            throw new MessagingException("이메일 전송에 실패하였습니다.", e);
        }
    }

    public String setContext(String code) {
        Context context = new Context();
        String emailContent =
                "<h1>안녕하세요.</h1>\n" +
                        "<h1>KrowdPoping 입니다.</h1>\n" +
                        "<br>\n" +
                        "<p>인증코드입니다:</p>\n" +
                        "<p style=\"font-size:130%;\">" + code + "</p>\n" +
                        "<p>회원가입 페이지로 돌아가 인증코드를 입력해주세요.</p>\n";

        context.setVariable("code", code);
        return emailContent;
    }




}
