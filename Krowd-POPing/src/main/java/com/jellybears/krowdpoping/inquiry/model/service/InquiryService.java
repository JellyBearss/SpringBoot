package com.jellybears.krowdpoping.inquiry.model.service;

import com.jellybears.krowdpoping.common.exception.inquiry.InquirySaveException;
import com.jellybears.krowdpoping.inquiry.model.dto.InquiryDTO;

import java.util.List;

public interface InquiryService {

    //관리자에게 문의 조회용. 잠시 주석
//    List<InquiryDTO> findInquiryList();

    //창작자에게 문의 조회용
    List<InquiryDTO> findInquiryList2();

    //문의 작성
    void saveNewInquiry(InquiryDTO inquiry) throws InquirySaveException;

    //문의글 상세보기
    InquiryDTO selectInquiryContent(Long no);

    //로그인한 유저 코드에 따른 문의 리스트 보기
    List<InquiryDTO> findInquiryListByUSerCode(int userCode);


}
