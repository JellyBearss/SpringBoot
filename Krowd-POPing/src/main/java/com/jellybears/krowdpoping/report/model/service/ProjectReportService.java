package com.jellybears.krowdpoping.report.model.service;

import com.jellybears.krowdpoping.common.thumbnail.ThumbnailRegistException;
import com.jellybears.krowdpoping.report.model.dto.ProjectReportDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProjectReportService {
    List<ProjectReportDTO> findReportList();

    void saveReport(ProjectReportDTO projectReportDTO) throws ThumbnailRegistException;


//    void saveReport(ProjectReportDTO projectReportDTO, MultipartFile file) throws IOException;


}
