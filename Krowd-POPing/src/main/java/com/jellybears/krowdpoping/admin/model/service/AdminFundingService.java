package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.admin.model.dto.AdminFundingDTO;
import com.jellybears.krowdpoping.common.exception.admin.notice.FundingModifyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminFundingService {
    /* 펀딩 전체 목록 조회 메서드 */
    List<AdminFundingDTO> selectAllFundingList();
    /* 펀딩 상세 조회 메서드 */
    AdminFundingDTO selectFundingDetail(int projectCode);
    /* 펀딩 갱신 메서드 */
    void fundingModify(AdminFundingDTO funding) throws FundingModifyException;
}
