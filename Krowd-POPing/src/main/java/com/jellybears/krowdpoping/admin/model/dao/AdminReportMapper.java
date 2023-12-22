package com.jellybears.krowdpoping.admin.model.dao;

import com.jellybears.krowdpoping.report.model.dto.ProjectReportDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminReportMapper {
    /* 신고 전체 목록 조회 */
    List<ProjectReportDTO> selectAllReportList();
}
