package com.jellybears.krowdpoping.admin.model.service;

import com.jellybears.krowdpoping.report.model.dto.ProjectReportDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminReportService {

    /* 신고 전체 목록 조회 메서드 */
    List<ProjectReportDTO> selectAllReportList();
}
