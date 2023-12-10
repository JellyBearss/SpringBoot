package com.jellybears.krowdpoping.user.model.service;


import com.jellybears.krowdpoping.common.exception.member.UserRegistException;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;

public interface UserService {

    //회원 조회용 method
    public boolean selectUserById(String id);
    /* 회원 가입용 메소드 */
//    public void registUser(UserDTO user)throws UserRegistException;
//회원 정보 수정 추가 예정
//회원 삭제 추가 예정
}
