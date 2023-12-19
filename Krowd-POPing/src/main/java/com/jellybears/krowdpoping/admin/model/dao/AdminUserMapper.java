package com.jellybears.krowdpoping.admin.model.dao;

import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminUserMapper {
    /* 관리자 -------------------------------------------------------------------------------------------------------------- */
    /* 회원 전체 목록 조회 */
    List<UserDTO> selectAllUserList();

    /* 회원 상세 조회 */
    UserDTO selectUserDetail(int user_code);
    /* ----------------------------------------------------------------------------------------------------------------- */
}
