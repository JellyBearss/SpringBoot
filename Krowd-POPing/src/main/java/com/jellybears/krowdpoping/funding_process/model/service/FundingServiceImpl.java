package com.jellybears.krowdpoping.funding_process.model.service;

import com.jellybears.krowdpoping.common.exception.address.AddressSaveException;
import com.jellybears.krowdpoping.funding_process.model.dao.FundingMapper;
import com.jellybears.krowdpoping.funding_process.model.dto.AddressDTO;

import com.jellybears.krowdpoping.funding_process.model.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class FundingServiceImpl implements FundingService {

    private final FundingMapper fundingMapper;

    @Autowired
    public FundingServiceImpl(FundingMapper fundingMapper){
        this.fundingMapper = fundingMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAddress(AddressDTO addressDTO) throws AddressSaveException {
        log.info("[AddressService] Save Address : " + addressDTO);

        if ("type".equals(addressDTO.getDeliveryComment())) {
            addressDTO.setDeliveryComment(addressDTO.getDirectInputField2());
        }

        int result = fundingMapper.saveAddress(addressDTO);

        log.info("[FundingService] Insert result : " + ((result > 0) ? "주소 저장 성공" : "주소 저장 실패"));

        if(!(result > 0 )){
            throw new AddressSaveException("주소 저장에 실패하였습니다.");
        }
    }
    @Override
    public AddressDTO getDefaultAddress(String user_code) {
        return fundingMapper.getDefaultAddress(user_code);
    }

    public void savePaymentInfo(ProductDTO productDTO) {
        log.info("[FundingService] 결제 정보 저장: " + productDTO);

        productDTO.generateRandomValues();

        // FundingMapper에 결제 정보를 저장하기 위한 메서드를 가정합니다.
        int result = fundingMapper.savePaymentInfo(productDTO);

        log.info("[FundingService] 삽입 결과: " + ((result > 0) ? "결제 정보 저장 성공" : "결제 정보 저장 실패"));
    }
}
