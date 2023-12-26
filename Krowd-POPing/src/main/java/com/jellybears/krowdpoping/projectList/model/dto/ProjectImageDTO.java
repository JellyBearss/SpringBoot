package com.jellybears.krowdpoping.projectList.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectImageDTO {
    private int projectfileCode;
    private int projectCode;
    private String originalName;
    private String savedName;
    private String savePath;
    private String fileType;
    private String thumbnailPath;
    private String whetherDeleted;
    private Date updateDate;
}
