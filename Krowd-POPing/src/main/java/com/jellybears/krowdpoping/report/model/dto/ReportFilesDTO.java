package com.jellybears.krowdpoping.report.model.dto;

import lombok.*;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReportFilesDTO {

    private int fileNo; //파일번호
    private String originFileName; //원본파일명
    private String ChangedFileName; //변경된 파일명
    private Date updateDate; //업로드일자
    private String WhetherDeleted; //삭제여부
    private int reportType; //신고유형
}
