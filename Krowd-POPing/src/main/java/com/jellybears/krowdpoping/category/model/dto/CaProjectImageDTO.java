package com.jellybears.krowdpoping.category.model.dto;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CaProjectImageDTO {

    private int projectFileCode;
    private String originFileName;
    private String changedFileName;
    private Date updateDate;
    private String whetherDeleted;
    private String fileType;
    private int refProjectCode;
    private String savePath;
    private String thumbnailPath;

    public int getProjectFileCode() {
        return projectFileCode;
    }

    public void setProjectFileCode(int projectFileCode) {
        this.projectFileCode = projectFileCode;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public String getChangedFileName() {
        return changedFileName;
    }

    public void setChangedFileName(String changedFileName) {
        this.changedFileName = changedFileName;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getWhetherDeleted() {
        return whetherDeleted;
    }

    public void setWhetherDeleted(String whetherDeleted) {
        this.whetherDeleted = whetherDeleted;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getRefProjectCode() {
        return refProjectCode;
    }

    public void setRefProjectCode(int refProjectCode) {
        this.refProjectCode = refProjectCode;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }
}
