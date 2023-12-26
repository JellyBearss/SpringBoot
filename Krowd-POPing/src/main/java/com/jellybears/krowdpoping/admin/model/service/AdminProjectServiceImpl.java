package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.admin.model.dao.AdminProjectMapper;
import com.jellybears.krowdpoping.admin.model.dto.AdminFundingDTO;
import com.jellybears.krowdpoping.common.exception.admin.notice.FundingAcceptException;
import com.jellybears.krowdpoping.common.exception.admin.notice.FundingRejectException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminProjectServiceImpl implements AdminProjectService {

    private final AdminProjectMapper adminProjectMapper;

    public AdminProjectServiceImpl(AdminProjectMapper adminProjectMapper) {

        this.adminProjectMapper = adminProjectMapper;
    }

    /**
     * 프로젝트 목록 조회 메서드
     * @return
     */
    @Override
    public List<AdminFundingDTO> selectAllProjectList() {

        return adminProjectMapper.selectAllProjectList();
    }

    /**
     * 프로젝트 상세 조회 메서드
     * @param projectCode
     * @return
     */
    @Override
    public AdminFundingDTO selectProjectDetail(int projectCode) {
        AdminFundingDTO projectDetail = null;

        projectDetail = adminProjectMapper.selectProjectDetail(projectCode);
        return projectDetail;
    }

    /**
     * 프로젝트 승인
     * @param adminFundingDTO
     * @throws FundingAcceptException
     */
    @Override
    @Transactional
    public void fundingAccept(AdminFundingDTO adminFundingDTO) throws FundingAcceptException {

        int result = adminProjectMapper.fundingAccept(adminFundingDTO);

        if (!(result > 0)) {
            throw new FundingAcceptException("승인을 실패하였습니다.");
        }
    }

    /**
     * 프로젝트 반려
     * @param adminFundingDTO
     * @throws FundingRejectException
     */
    @Override
    @Transactional
    public void fundingReject(AdminFundingDTO adminFundingDTO) throws FundingRejectException {

        int result = adminProjectMapper.fundingReject(adminFundingDTO);

        if (!(result > 0)) {
            throw new FundingRejectException("승인을 실패하였습니다.");
        }
    }
}
