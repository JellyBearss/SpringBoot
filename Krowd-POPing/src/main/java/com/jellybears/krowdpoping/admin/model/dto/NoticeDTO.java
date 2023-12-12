package com.jellybears.krowdpoping.admin.model.dto;

import com.jellybears.krowdpoping.user.model.dto.UserDTO;

import java.util.Date;

public class NoticeDTO {

    private int noticeCode;
    private String title;
    private String content;
    private Date updateDate;
    private String type;
    private int adminUserCode;
    private UserDTO userCode;

    public NoticeDTO() {
    }

    public NoticeDTO(int noticeCode, String title, String content, Date updateDate, String type, int adminUserCode, UserDTO userCode) {
        this.noticeCode = noticeCode;
        this.title = title;
        this.content = content;
        this.updateDate = updateDate;
        this.type = type;
        this.adminUserCode = adminUserCode;
        this.userCode = userCode;
    }


    public int getNoticeCode() {
        return noticeCode;
    }

    public void setNoticeCode(int noticeCode) {
        this.noticeCode = noticeCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAdminUserCode() {
        return adminUserCode;
    }

    public void setAdminUserCode(int adminUserCode) {
        this.adminUserCode = adminUserCode;
    }

    public UserDTO getUserCode() {
        return userCode;
    }

    public void setUserCode(UserDTO userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "NoticeDTO{" +
                "noticeCode=" + noticeCode +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", updateDate=" + updateDate +
                ", type='" + type + '\'' +
                ", adminUserCode=" + adminUserCode +
                ", userCode=" + userCode +
                '}';
    }
}
