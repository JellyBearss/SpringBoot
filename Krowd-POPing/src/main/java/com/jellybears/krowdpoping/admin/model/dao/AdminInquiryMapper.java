package com.jellybears.krowdpoping.admin.model.dao;

import com.jellybears.krowdpoping.inquiry.model.dto.InquiryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminInquiryMapper {

    /* 문의 전체 목록 조회 */
    List<InquiryDTO> selectAllInquiryList();
}
