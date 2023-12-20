package com.jellybears.krowdpoping.projectRegister.section01.model.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ThumbnailDTO {

    private int projectFileCode;

    private String originalName;

    private String changedName;

    private Date updateDate;
    private String whetherDeleted;

    private String fileType;

    private int projectCode;
    private String savePath;
    private String thumbnailPath;

}
