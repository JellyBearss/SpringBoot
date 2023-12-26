package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.admin.model.dao.AdminFundingMapper;
import com.jellybears.krowdpoping.admin.model.dto.AdminFundingDTO;
import com.jellybears.krowdpoping.common.exception.admin.notice.FundingModifyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminFundingServiceImpl implements AdminFundingService {

    private final AdminFundingMapper adminFundingMapper;

    public AdminFundingServiceImpl(AdminFundingMapper adminFundingMapper) {

        this.adminFundingMapper = adminFundingMapper;
    }

    /**
     * 펀딩 목록 조회 메서드
     * @return
     */
    @Override
    public List<AdminFundingDTO> selectAllFundingList() {

        return adminFundingMapper.selectAllFundingList();
    }

    /**
     * 펀딩 상세 조회 메서드
     * @param projectCode
     * @return
     */
    @Override
    public AdminFundingDTO selectFundingDetail(int projectCode) {
        AdminFundingDTO fundingDetail = null;

        fundingDetail = adminFundingMapper.selectFundingDetail(projectCode);
        return fundingDetail;
    }

    /**
     * 펀딩 수정 메서드
     * @param funding
     * @throws FundingModifyException
     */
    @Override
    @Transactional
    public void fundingModify(AdminFundingDTO funding) throws FundingModifyException {

        int result = adminFundingMapper.fundingModify(funding);

        if (!(result > 0)) {
            throw new FundingModifyException("펀딩 수정을 실패하였습니다.");
        }
    }
}
