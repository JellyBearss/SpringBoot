package com.jellybears.krowdpoping.funding_process.model.service;

import com.jellybears.krowdpoping.common.exception.address.AddressSaveException;
import com.jellybears.krowdpoping.funding_process.model.dao.AddressMapper;
import com.jellybears.krowdpoping.funding_process.model.dto.AddressDTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService{

    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressMapper addressMapper){
        this.addressMapper = addressMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAddress(AddressDTO addressDTO) throws AddressSaveException {
        log.info("[AddressService] Save Address : " + addressDTO);

        if ("type".equals(addressDTO.getDeliveryComment())) {
            addressDTO.setDeliveryComment(addressDTO.getDirectInputField2());
        }

        int result = addressMapper.saveAddress(addressDTO);

        log.info("[AddressService] Insert result : " + ((result > 0) ? "주소 저장 성공" : "주소 저장 실패"));

        if(!(result > 0 )){
            throw new AddressSaveException("주소 저장에 실패하였습니다.");
        }
    }
    @Override
    public AddressDTO getDefaultAddress(String user_code) {
        return addressMapper.getDefaultAddress(user_code);
    }
}
