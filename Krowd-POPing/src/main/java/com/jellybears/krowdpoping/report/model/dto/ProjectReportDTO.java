package com.jellybears.krowdpoping.report.model.dto;

import com.jellybears.krowdpoping.category.model.dto.CategoryDTO;
import lombok.*;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProjectReportDTO {

    private int projectReportCode; //게시물번호
    private String reportTitle; //제목
    private String reportContent; //내용
    private String reporter; //신고한 유저 - 유저 코드에서 가져옴
    private int reportProjectCode; //신고대상 프로젝트 - 프로젝트 코드에서 가져옴
    private Date reportDate; //신고일자
    private String whetherDeleted; //삭제여부
    private String whetherCompleted; //처리상태
    private Date completedDate; //처리완료일자
    private ProjectReportCategoryDTO category;


}
