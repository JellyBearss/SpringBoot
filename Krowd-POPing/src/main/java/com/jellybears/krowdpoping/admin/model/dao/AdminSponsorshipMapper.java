package com.jellybears.krowdpoping.admin.model.dao;

import com.jellybears.krowdpoping.admin.model.dto.AdminSponsorshipDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminSponsorshipMapper {

    /* 후원 전체 목록 조회 */
    List<AdminSponsorshipDTO> selectAllSponsorshipList();
}
