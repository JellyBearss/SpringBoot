package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.admin.model.dao.AdminInquiryMapper;
import com.jellybears.krowdpoping.inquiry.model.dto.InquiryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdminInquiryServiceImpl implements AdminInquiryService{

    private final AdminInquiryMapper adminInquiryMapper;

    public AdminInquiryServiceImpl(AdminInquiryMapper adminInquiryMapper) {

        this.adminInquiryMapper = adminInquiryMapper;
    }

    /**
     * 문의 목록 조회 메서드
     * @return
     */
    @Override
    public List<InquiryDTO> selectAllInquiryList() {

        return adminInquiryMapper.selectAllInquiryList();
    }
}
