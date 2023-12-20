package com.jellybears.krowdpoping.funding_process.model.dao;

import com.jellybears.krowdpoping.funding_process.model.dto.AddressDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper {

    int saveAddress(AddressDTO addressDTO);

    AddressDTO getDefaultAddress(String user_code);
}
