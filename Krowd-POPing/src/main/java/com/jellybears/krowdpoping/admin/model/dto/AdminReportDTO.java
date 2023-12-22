package com.jellybears.krowdpoping.admin.model.dto;

import com.jellybears.krowdpoping.report.model.dto.ProjectReportCategoryDTO;
import com.jellybears.krowdpoping.report.model.dto.ReportFilesDTO;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminReportDTO {

    private int projectReportCode;                  // (PK) 게시물 번호
    private String reportTitle;                     // 제목
    private String reportContent;                   // 내용
    private String reporter;                        // (FK) 신고한 유저 - 유저 코드에서 가져옴
    private int reportProjectCode;                  // (FK) 신고대상 프로젝트 - 프로젝트 코드에서 가져옴
    // private ProjectReportCategoryDTO category;      // (FK) 프로젝트 신고 사유 카테고리
    private Date reportDate;                        // 신고 일자
    private String whetherDeleted;                  // 삭제 여부
    private String whetherCompleted;                // 처리 상태
    private Date completedDate;                     // 처리 완료 일자

    private int reportAdminUserCode;
    private UserDTO userDTO;


    // private List<ReportFilesDTO> reportFilesList;

    // private int projectCode;
    // private String projectTitle;
}
