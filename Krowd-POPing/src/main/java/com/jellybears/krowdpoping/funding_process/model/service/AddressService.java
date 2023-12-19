package com.jellybears.krowdpoping.funding_process.model.service;

import com.jellybears.krowdpoping.common.exception.address.AddressSaveException;
import com.jellybears.krowdpoping.funding_process.model.dao.AddressMapper;
import com.jellybears.krowdpoping.funding_process.model.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;

public interface AddressService {
    public void saveAddress(AddressDTO addressDTO) throws AddressSaveException;

    AddressDTO getDefaultAddress(String userCode);
}
