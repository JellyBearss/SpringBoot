package com.jellybears.krowdpoping.user.model.dto;

import lombok.*;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileImgDTO {

    private int file_code;
    private String original_file_name;
    private String edited_file_name;
    private Date upload_date;
    private String whether_deleted;
    private int user_code;

}
