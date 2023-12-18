package com.jellybears.krowdpoping.admin.model.dto;

import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
public class NoticeDTO {

    private int noticeCode;
    private String title;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;
    private String type;
    private int count;
    private int noticeAdminUserCode;
    private UserDTO userDTO;

    public NoticeDTO() {
    }

    public NoticeDTO(int noticeCode, String title, String content, Date updateDate, String type, int count, int noticeAdminUserCode, UserDTO userDTO) {
        this.noticeCode = noticeCode;
        this.title = title;
        this.content = content;
        this.updateDate = updateDate;
        this.type = type;
        this.count = count;
        this.noticeAdminUserCode = noticeAdminUserCode;
        this.userDTO = userDTO;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNoticeAdminUserCode() {
        return noticeAdminUserCode;
    }

    public void setNoticeAdminUserCode(int noticeAdminUserCode) {
        this.noticeAdminUserCode = noticeAdminUserCode;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "NoticeDTO{" +
                "noticeCode=" + noticeCode +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", updateDate=" + updateDate +
                ", type='" + type + '\'' +
                ", count=" + count +
                ", noticeAdminUserCode=" + noticeAdminUserCode +
                ", userDTO=" + userDTO +
                '}';
    }
}
