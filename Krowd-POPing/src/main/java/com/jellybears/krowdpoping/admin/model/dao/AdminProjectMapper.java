package com.jellybears.krowdpoping.admin.model.dao;

import com.jellybears.krowdpoping.admin.model.dto.AdminFundingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminProjectMapper {
    /* 프로젝트 전체 목록 조회 */
    List<AdminFundingDTO> selectAllProjectList();
    /* 프로젝트 상세 목록 조회 */
    AdminFundingDTO selectProjectDetail(int projectCode);
    /* 프로젝트 승인 */
    int fundingAccept(AdminFundingDTO adminFundingDTO);
    /* 프로젝트 반려 */
    // int fundingReject(AdminFundingDTO adminFundingDTO);
}
