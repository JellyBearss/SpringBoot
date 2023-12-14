package com.jellybears.krowdpoping.user.model.service;


import com.jellybears.krowdpoping.common.exception.user.UserModifyException;
import com.jellybears.krowdpoping.common.exception.user.UserRegistException;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import org.springframework.stereotype.Service;


public interface UserService {



    //회원 조회용 method
    public boolean selectUserById(String userId);
    /* 회원 가입용 메소드 */
    public void registUser(UserDTO userDTO)throws UserRegistException;

    //회원 정보 수정 추가
    public void modifyMember(UserDTO user) throws UserModifyException;
//회원 삭제 추가 예정
}
