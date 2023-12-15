package com.jellybears.krowdpoping.inquiry.model.service;

import com.jellybears.krowdpoping.common.exception.inquiry.InquirySaveException;
import com.jellybears.krowdpoping.inquiry.model.dto.InquiryDTO;

import java.util.List;

public interface InquiryService {


    List<InquiryDTO> findInquiryList();
    void saveNewInquiry(InquiryDTO inquiry) throws InquirySaveException;


    InquiryDTO selectInquiryContent(Long no);
}
