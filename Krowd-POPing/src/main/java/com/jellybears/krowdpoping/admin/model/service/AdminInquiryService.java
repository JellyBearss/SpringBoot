package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.inquiry.model.dto.InquiryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminInquiryService {

    /* 문의 전체 목록 조회 메서드 */
    List<InquiryDTO> selectAllInquiryList();
}
