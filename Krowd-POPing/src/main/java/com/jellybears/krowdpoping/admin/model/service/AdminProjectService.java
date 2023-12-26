package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.admin.model.dto.AdminFundingDTO;
import com.jellybears.krowdpoping.common.exception.admin.notice.FundingAcceptException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AdminProjectService {
    /* 프로젝트 전체 목록 조회 메서드 */
    List<AdminFundingDTO> selectAllProjectList();
    /* 프로젝트 상세 조회 메서드 */
    AdminFundingDTO selectProjectDetail(int projectCode);
    /* 프로젝트 승인 */
    void fundingAccept(AdminFundingDTO adminFundingDTO) throws FundingAcceptException;
    /* 프로젝트 반려 */
    // int fundingReject(AdminFundingDTO adminFundingDTO);
}
