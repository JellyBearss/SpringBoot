package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.admin.model.dao.AdminFundingMapper;
import com.jellybears.krowdpoping.admin.model.dto.AdminFundingDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminFundingServiceImpl implements AdminFundingService{

    private final AdminFundingMapper adminFundingMapper;

    public AdminFundingServiceImpl(AdminFundingMapper adminFundingMapper) {

        this.adminFundingMapper = adminFundingMapper;
    }

    /**
     * 펀딩 목록 조회 메서드
     * @return
     */
    @Override
    public List<AdminFundingDTO> selectAllFundingList() {

        return adminFundingMapper.selectAllFundingList();
    }
}
