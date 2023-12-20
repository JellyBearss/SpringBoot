package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.admin.model.dto.AdminSponsorshipDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AdminSponsorshipService {

    /* 후원 전체 목록 조회 메서드 */
    List<AdminSponsorshipDTO> selectAllSponsorshipList();
}
