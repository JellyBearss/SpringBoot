package com.jellybears.krowdpoping.user.model.service;


import com.jellybears.krowdpoping.common.exception.user.UserModifyException;
import com.jellybears.krowdpoping.common.exception.user.UserRegistException;
import com.jellybears.krowdpoping.common.exception.user.UserRemoveException;
import com.jellybears.krowdpoping.user.model.dto.EmailDTO;
import com.jellybears.krowdpoping.user.model.dto.EmailandUserDTO;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import org.springframework.stereotype.Service;


public interface UserService {



    //회원 조회용 method
    public boolean selectUserById(String userId);
    /* 회원 가입용 메소드 */
    public void registUser(UserDTO user)throws UserRegistException;

    //회원 정보 수정
    public void modifyUser(UserDTO user) throws UserModifyException;

    /*-------------회원 탈퇴------------*/
    void removeUser(UserDTO user) throws UserRemoveException;

//    public int insertEmailCode(EmailDTO emailDTO) throws Exception;
//
//    public int updateEmailCertificate(EmailandUserDTO emailandUserDTO)throws Exception;


//회원 삭제 추가 예정
}
