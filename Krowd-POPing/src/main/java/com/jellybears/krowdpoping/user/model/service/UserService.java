package com.jellybears.krowdpoping.user.model.service;


import com.jellybears.krowdpoping.common.exception.user.UserModifyException;
import com.jellybears.krowdpoping.common.exception.user.UserRegistException;
import com.jellybears.krowdpoping.common.exception.user.UserRemoveException;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import jakarta.servlet.http.HttpServletResponse;


public interface UserService {



    //회원 조회용 method
    public boolean selectUserById(String userId);
    /* 회원 가입용 메소드 */
    public void registUser(UserDTO user)throws UserRegistException;

    //회원 정보 수정
    public void modifyUser(UserDTO user) throws UserModifyException;

    /*-------------회원 탈퇴------------*/
    public void removeUser(UserDTO user) throws UserRemoveException;
    /*-------------이메일로 아이디 찾기 ------------*/
    public UserDTO findIdByEmail(String email) throws Exception;
    public int findIdCheck(String email) throws Exception;
    /*-------------이메일로 비밀번호 찾기------------*/
    public void find_pwd(HttpServletResponse response, UserDTO user) throws Exception;
    /*------------임시 비밀번호 보내기------------*/
    public String send_PwdMail(UserDTO user) throws Exception;



}





