package com.jellybears.krowdpoping.funding_process.model.service;

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
    @Transactional
    public void saveAddress(AddressDTO addressDTO) {
        log.info("AddressService] Save Address : " + addressDTO);
        addressMapper.saveAddress(addressDTO);
        log.info("[AddressService] Save result : 주소 저장 완료");
    }
}
