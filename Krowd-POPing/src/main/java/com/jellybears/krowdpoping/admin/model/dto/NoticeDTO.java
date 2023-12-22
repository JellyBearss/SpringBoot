package com.jellybears.krowdpoping.admin.model.dto;

import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
}
