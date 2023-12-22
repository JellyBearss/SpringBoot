package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.admin.model.dao.AdminProjectMapper;
import com.jellybears.krowdpoping.admin.model.dto.AdminFundingDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminProjectServiceImpl implements AdminProjectService {

    private final AdminProjectMapper adminProjectMapper;

    public AdminProjectServiceImpl(AdminProjectMapper adminProjectMapper) {

        this.adminProjectMapper = adminProjectMapper;
    }

    /**
     * 프로젝트 목록 조회 메서드
     * @return
     */
    @Override
    public List<AdminFundingDTO> selectAllProjectList() {

        return adminProjectMapper.selectAllProjectList();
    }


}
