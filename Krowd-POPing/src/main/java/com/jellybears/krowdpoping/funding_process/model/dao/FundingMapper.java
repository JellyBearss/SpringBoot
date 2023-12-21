package com.jellybears.krowdpoping.funding_process.model.dao;

import com.jellybears.krowdpoping.funding_process.model.dto.AddressDTO;
import com.jellybears.krowdpoping.funding_process.model.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FundingMapper {

    int saveAddress(AddressDTO addressDTO);

    AddressDTO getDefaultAddress(String user_code);

    int saveDetailedProduct(int userCode, int goodsCode);
}
