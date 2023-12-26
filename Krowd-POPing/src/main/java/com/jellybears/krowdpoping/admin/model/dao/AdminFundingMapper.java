package com.jellybears.krowdpoping.admin.model.dao;

import com.jellybears.krowdpoping.admin.model.dto.AdminFundingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminFundingMapper {
    /* 펀딩 전체 목록 조회 */
    List<AdminFundingDTO> selectAllFundingList();
    /* 펀딩 상세 조회 */
    AdminFundingDTO selectFundingDetail(int projectCode);
    /* 펀딩 갱신 */
    int fundingModify(AdminFundingDTO funding);
}
