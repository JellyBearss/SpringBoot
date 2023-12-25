package com.jellybears.krowdpoping.project.model.dto;

import lombok.*;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetailProjectImageDTO {

    private int projectFileCode;
    private String originFileName;
    private String changedFileName;
    private Date updateDate;
    private String whetherDeleted;
    private String fileType;
    private int refProjectCode;
    private String savePath;
    private String thumbnailPath;
}
