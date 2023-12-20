package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.admin.model.dto.AdminFundingDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AdminProjectService {
    List<AdminFundingDTO> selectAllProjectList();

}
