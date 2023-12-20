package com.jellybears.krowdpoping.funding_process.model.service;

import com.jellybears.krowdpoping.common.exception.address.AddressSaveException;
import com.jellybears.krowdpoping.funding_process.model.dto.AddressDTO;

public interface FundingService {
    public void saveAddress(AddressDTO addressDTO) throws AddressSaveException;

    AddressDTO getDefaultAddress(String userCode);

}
