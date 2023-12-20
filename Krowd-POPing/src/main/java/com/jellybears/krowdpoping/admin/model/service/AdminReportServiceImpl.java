package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.admin.model.dao.AdminReportMapper;
import com.jellybears.krowdpoping.report.model.dto.ProjectReportDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminReportServiceImpl implements AdminReportService {

    private final AdminReportMapper adminReportMapper;

    public AdminReportServiceImpl(AdminReportMapper adminReportMapper) {

        this.adminReportMapper = adminReportMapper;
    }

    /**
     * 신고 목록 조회 메서드
     * @return
     */
    @Override
    public List<ProjectReportDTO> selectAllReportList() {
        return adminReportMapper.selectAllReportList();
    }
}
