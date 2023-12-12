package com.jellybears.krowdpoping.inquiry.model.dto;

import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import lombok.*;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InquiryDTO {

    private int inquiryCode;
    private String inquiryType;
    private String inquiryTitle;
    private String inquiryContent;
    private Date inquiryDate;
    private String inquiryStatus;
    private int questioner;
    private int replier;


}
