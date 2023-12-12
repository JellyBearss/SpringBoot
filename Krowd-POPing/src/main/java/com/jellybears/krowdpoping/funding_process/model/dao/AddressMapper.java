package com.jellybears.krowdpoping.funding_process.model.dao;

import com.jellybears.krowdpoping.funding_process.model.dto.AddressDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper {
    void saveAddress(AddressDTO addressDTO);
}
