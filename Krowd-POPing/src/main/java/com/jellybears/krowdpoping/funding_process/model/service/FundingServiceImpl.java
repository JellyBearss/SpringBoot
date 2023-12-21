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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDetailedProduct(ProductDTO productDTO) {
        int user_code = productDTO.getUser_code();
        int goodsCode = productDTO.getGoodsCode();

        int result = fundingMapper.saveDetailedProduct(user_code, goodsCode);
        log.info("[FundingService] Insert result: " + ((result > 0) ? "상세 상품 정보 저장 성공" : "상세 상품 정보 저장 실패"));
    }
}
