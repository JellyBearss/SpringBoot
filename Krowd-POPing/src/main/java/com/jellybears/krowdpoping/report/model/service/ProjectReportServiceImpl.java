package com.jellybears.krowdpoping.report.model.service;

import com.jellybears.krowdpoping.common.thumbnail.ThumbnailRegistException;
import com.jellybears.krowdpoping.report.model.dao.ProjectReportMapper;
import com.jellybears.krowdpoping.report.model.dto.ProjectReportDTO;
import com.jellybears.krowdpoping.report.model.dto.ReportFilesDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectReportServiceImpl implements ProjectReportService{

    private final ProjectReportMapper projectReportMapper;

    public ProjectReportServiceImpl(ProjectReportMapper projectReportMapper) {
        this.projectReportMapper = projectReportMapper;
    }

    @Override
    public List<ProjectReportDTO> findReportList() {
        return projectReportMapper.findReportList();
    }

//    @Override
//    @Transactional
//    public void saveReport(ProjectReportDTO projectReportDTO) {
//
//
//
//        int result = projectReportMapper.saveReport(projectReportDTO);
//
//    }


    @Override
    @Transactional
    public void saveReport(ProjectReportDTO projectReportDTO) throws ThumbnailRegistException {

        int result = 0;

        //작성내용부터 insert
        int reportResult = projectReportMapper.saveReport(projectReportDTO);

        //reportfiles 불러오기

        List<ReportFilesDTO> reportFilesList = projectReportDTO.getReportFilesList();

        //fileList에 projectReportCode 넣는다.

//        for(int i = 0; i < reportFilesList.size(); i++){
//            reportFilesList.get(i).setRefReportCode(projectReportDTO.getProjectReportCode());
//        }

        for(ReportFilesDTO reportFilesDTO : projectReportDTO.getReportFilesList()) {
            reportFilesDTO.setRefReportCode(projectReportDTO.getProjectReportCode());
        }

        //reportFiles 테이블에 list size만큼 insert

        int reportFilesResult = 0;
        for(int i = 0; i < reportFilesList.size(); i ++){

            reportFilesResult += projectReportMapper.insertReportFiles(reportFilesList.get(i));
        }

        //게시글 추가 및 첨부파일 갯수만큼 첨부파일 내용 insert에 실패 시 예외 발생
        if(!(reportResult > 0 && reportFilesResult == reportFilesList.size())){
            throw new ThumbnailRegistException("등록에 실패하셨습니다.");
        }

    }

//    @Override
//    @Transactional
//    public void saveReport(ProjectReportDTO projectReportDTO, MultipartFile file) throws IOException {
//
//        String path ="C:\\upload-files"; //저장경로 지정
//        UUID uuid = UUID.randomUUID(); //랜덤생성
//        String filename = uuid + "_" + file.getOriginalFilename(); //랜덤이름붙고 _ 붙이고 원래 파일이름
//        File saveFile = new File(path, filename); //saveFile을 생성해주는데, 경로와 파일이름을 넣어준다.
//        file.transferTo(saveFile); //업로드된 파일을 지정된 경로에 저장
//
//        projectReportMapper.saveReport(projectReportDTO, file);
//
//    }


}
