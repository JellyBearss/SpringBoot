package com.jellybears.krowdpoping.inquiry.model.dao;

import com.jellybears.krowdpoping.inquiry.model.dto.InquiryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {
    List<InquiryDTO> findInquiryList();
    List<InquiryDTO> findInquiryList2();
    int saveNewInquiry(InquiryDTO inquiry);
    InquiryDTO selectInquiryContent(Long no);

}
