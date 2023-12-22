package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.admin.model.dao.AdminUserMapper;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserMapper adminUserMapper;

    public AdminUserServiceImpl(AdminUserMapper adminUserMapper) {

        this.adminUserMapper = adminUserMapper;
    }

    /* 관리자 ---------------------------------------------------------------------------------------------------------- */
    /**
     * 회원 목록 조회 메서드
     * @return
     */
    @Override
    public List<UserDTO> selectAllUserList() {

        return adminUserMapper.selectAllUserList();
    }

    /**
     * 회원 상세 조회 메서드
     * @param user_code
     * @return
     */
    @Override
    public UserDTO selectUserDetail(int user_code) {
        UserDTO userDetail = null;

        userDetail = adminUserMapper.selectUserDetail(user_code);
        return userDetail;
    }

    /* ----------------------------------------------------------------------------------------------------------------- */

}
