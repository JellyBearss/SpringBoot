package com.jellybears.krowdpoping.funding_process.model.service;

import com.jellybears.krowdpoping.common.exception.address.AddressSaveException;
import com.jellybears.krowdpoping.funding_process.model.dto.AddressDTO;
import com.jellybears.krowdpoping.funding_process.model.dto.ProductDTO;

public interface FundingService {
    public void saveAddress(AddressDTO addressDTO) throws AddressSaveException;

    AddressDTO getDefaultAddress(String userCode);

    void savePaymentInfo(ProductDTO productDTO);

    void savePaymentStatus(ProductDTO productDTO);

    void saveCancel(ProductDTO productDTO);
}
