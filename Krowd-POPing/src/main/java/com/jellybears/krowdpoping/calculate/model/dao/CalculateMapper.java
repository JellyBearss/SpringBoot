package com.jellybears.krowdpoping.calculate.model.dao;

import com.jellybears.krowdpoping.calculate.model.dto.CalculateDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalculateMapper {

    CalculateDTO selectCalculate(Long no);

}
