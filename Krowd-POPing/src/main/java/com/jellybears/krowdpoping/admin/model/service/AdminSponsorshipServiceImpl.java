package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.admin.model.dao.AdminSponsorshipMapper;
import com.jellybears.krowdpoping.admin.model.dto.AdminSponsorshipDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminSponsorshipServiceImpl implements AdminSponsorshipService {

    private final AdminSponsorshipMapper adminSponsorshipMapper;

    public AdminSponsorshipServiceImpl(AdminSponsorshipMapper adminSponsorshipMapper) {

        this.adminSponsorshipMapper = adminSponsorshipMapper;
    }

    /**
     * 후원 목록 조회 메서드
     * @return
     */
    @Override
    public List<AdminSponsorshipDTO> selectAllSponsorshipList() {

        return adminSponsorshipMapper.selectAllSponsorshipList();
    }
}
