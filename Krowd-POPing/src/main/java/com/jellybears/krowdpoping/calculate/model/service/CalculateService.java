package com.jellybears.krowdpoping.calculate.model.service;

import com.jellybears.krowdpoping.calculate.model.dao.CalculateMapper;
import com.jellybears.krowdpoping.calculate.model.dto.CalculateDTO;
import org.springframework.stereotype.Service;

@Service
public class CalculateService {
    private final CalculateMapper calculateMapper;

    public CalculateService(CalculateMapper calculateMapper) {this.calculateMapper=calculateMapper;}

    public CalculateDTO selectCalculate(Long no) {
        return calculateMapper.selectCalculate(no);
    }
}
