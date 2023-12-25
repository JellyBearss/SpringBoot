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

    private int projectCode;

    private String originalName;

    private String savedName;

    private String savePath;

    private String fileType;

    private String thumbnailPath;

    private String whetherDeleted;

    private Date updateDate;






}
