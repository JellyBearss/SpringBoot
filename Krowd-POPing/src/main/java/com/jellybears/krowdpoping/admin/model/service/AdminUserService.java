package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminUserService {

    /* 관리자 ---------------------------------------------------------------------------------------------------------- */
    /* 회원 전체 목록 조회 메서드 */
    List<UserDTO> selectAllUserList();
    /* 회원 상세 조회 메서드 */
    UserDTO selectUserDetail(int user_code);
    /* ----------------------------------------------------------------------------------------------------------------- */

}
