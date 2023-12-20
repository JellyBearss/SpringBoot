package com.jellybears.krowdpoping.report.model.dao;

import com.jellybears.krowdpoping.report.model.dto.ProjectReportDTO;
import com.jellybears.krowdpoping.report.model.dto.ReportFilesDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface ProjectReportMapper {
    List<ProjectReportDTO> findReportList();
    int saveReport(ProjectReportDTO projectReportDTO);

    int insertReportFiles(ReportFilesDTO reportFilesDTO);
}
