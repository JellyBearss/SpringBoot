package com.jellybears.krowdpoping.inquiry.model.service;

import com.jellybears.krowdpoping.inquiry.model.dao.InquiryMapper;
import com.jellybears.krowdpoping.inquiry.model.dto.InquiryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryService {

    private final InquiryMapper inquiryMapper;

    public InquiryService(InquiryMapper inquiryMapper) {
        this.inquiryMapper = inquiryMapper;
    }

    public List<InquiryDTO> findInquiryList() {

        return inquiryMapper.findInquiryList();
    }
}
