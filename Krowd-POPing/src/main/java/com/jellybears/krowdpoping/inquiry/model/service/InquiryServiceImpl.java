package com.jellybears.krowdpoping.inquiry.model.service;

import com.jellybears.krowdpoping.common.exception.inquiry.InquirySaveException;
import com.jellybears.krowdpoping.inquiry.model.dao.InquiryMapper;
import com.jellybears.krowdpoping.inquiry.model.dto.InquiryDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InquiryServiceImpl implements InquiryService{

    private final InquiryMapper inquiryMapper;

    public InquiryServiceImpl(InquiryMapper inquiryMapper) {
        this.inquiryMapper = inquiryMapper;
    }



    @Override
    public List<InquiryDTO> findInquiryList() {

        return inquiryMapper.findInquiryList();
    }

    @Override
    public List<InquiryDTO> findInquiryList2() {
        return inquiryMapper.findInquiryList2();
    }
    @Override
    @Transactional //성공하면 커밋 실패하면 롤백
    public void saveNewInquiry(InquiryDTO inquiry) throws InquirySaveException {

        int result = inquiryMapper.saveNewInquiry(inquiry);

        if(!(result > 0)){
            throw new InquirySaveException("문의 전송 요청 실패하셨습니다.");
        }
    }

    @Override
    public InquiryDTO selectInquiryContent(Long no) {



        return inquiryMapper.selectInquiryContent(no);
    }




}

