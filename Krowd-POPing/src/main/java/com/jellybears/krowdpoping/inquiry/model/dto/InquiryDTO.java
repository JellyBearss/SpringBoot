package com.jellybears.krowdpoping.inquiry.model.dto;

import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import lombok.*;

import java.sql.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InquiryDTO {

    private int inquiryCode;
    private String inquiryType;
    private String inquiryTitle;
    private String inquiryContent;
    private Date inquiryDate;
    private String inquiryStatus;
    private UserDTO questioner;
    private UserDTO replier;


}
