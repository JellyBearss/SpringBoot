package com.jellybears.krowdpoping.user.model.service;


import com.jellybears.krowdpoping.common.exception.user.UserModifyException;
import com.jellybears.krowdpoping.common.exception.user.UserRegistException;

import com.jellybears.krowdpoping.common.exception.user.UserRemoveException;
import com.jellybears.krowdpoping.user.model.dao.UserMapper;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

//회원 정보 수정 추가 예정
//회원 삭제 추가 예정
@Service
@Slf4j
public class UserServiceImpl implements UserService{
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    private final EmailService emailService;



    public UserServiceImpl(PasswordEncoder passwordEncoder, UserMapper mapper, EmailService emailService) {
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;

        this.emailService = emailService;
    }



    /*-------------회원 조회-----------*/
    @Override
    public boolean selectUserById(String userId) {
        UserDTO result = mapper.selectUserById(userId);
        return result !=null? true : false;
    }


    /*-----------회원 가입------------------*/
    @Override
    @Transactional
    public void registUser(UserDTO user) throws UserRegistException{

        log.info("[UserService] Insert User : " + user);

        int result = mapper.insertUser(user);

        int userCode = mapper.selectLastInsertUserCode();

        System.out.println(userCode+"===================user_code===============");
        log.info(String.valueOf(result));
        log.info(String.valueOf(userCode));

        int result1 = mapper.insertRoletype(userCode);


        System.out.println("result1.getUserId()========== : ");


        log.info("[UserService] Insert User : " + ((result > 0) ? "회원가입 성공" : "회원가입 실패"));

        if(!(result > 0 )){
            throw new UserRegistException("회원 가입에 실패하였습니다.");
        }
    }


    /*-------------회원 정보 수정------------*/

    @Override
    public void modifyUser(UserDTO user) throws UserModifyException {
        int result = mapper.updateUser(user);

        if(!(result > 0)) {
            throw new UserModifyException("회원 정보 수정에 실패하셨습니다.");
        }
    }


    /*-------------회원 탈퇴------------*/
    @Override
    public void removeUser(UserDTO user) throws UserRemoveException {
        int result = mapper.deleteUser(user);

        if(!(result > 0)) {
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

//    @Override
//    public void findPwd(String email) throws Exception {
//
//    }

//    @Override
//    public int findPwdCheck(UserDTO user) throws Exception {
//        mapper.findPwd(user.getPassword(),user.getEmail());
//
//        String Pwdkey=;
//
//        return mapper.findPwdCheck(user);
//
//
//
//    }





//        String password=
////        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
////        String memberKey = new TempKey().getKey(6,false);





//    @Override
//    public void findPw(String memberEmail,String memberId)throws Exception{
//        String memberKey = new TempKey().getKey(6,false);
//        String memberPw = BCrypt.hashpw(memberKey,BCrypt.gensalt());
//        memberDAO.findPw(memberEmail,memberId,memberPw);
//        MailUtils sendMail = new MailUtils(mailSender);
//        sendMail.setSubject("[ICEWATER 커뮤니티 임시 비밀번호 입니다.]"); //메일제목
//        sendMail.setText(
//                "<h1>임시비밀번호 발급</h1>" +
//                        "<br/>"+memberId+"님 "+
//                        "<br/>비밀번호 찾기를 통한 임시 비밀번호입니다."+
//                        "<br/>임시비밀번호 :   <h2>"+memberKey+"</h2>"+
//                        "<br/>로그인 후 비밀번호 변경을 해주세요."+
//                        "<a href='http://localhost:8080/member/loginView"+
//                        ">로그인 페이지</a>");
//        sendMail.setFrom("[보낼이메일]", "ICEWATER");
//        sendMail.setTo(memberEmail);
//        sendMail.send();
//    }



}






