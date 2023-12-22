package com.jellybears.krowdpoping.inquiry.model.dao;

import com.jellybears.krowdpoping.inquiry.model.dto.InquiryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {

//   //유저 코드 적용 전
//    List<InquiryDTO> findInquiryList();
    List<InquiryDTO> findInquiryList2();
    int saveNewInquiry(InquiryDTO inquiry);
    InquiryDTO selectInquiryContent(Long no);

    //유저 코드에 따른 문의 게시글 조회(본인이 작성한 게시물 조회)
    List<InquiryDTO> findInquiryListByUSerCode(int userCode);



}
