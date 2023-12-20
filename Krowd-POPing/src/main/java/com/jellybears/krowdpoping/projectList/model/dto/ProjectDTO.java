package com.jellybears.krowdpoping.projectList.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectDTO {
    private int projectCode;
    private String title;
    private int categoryCode;
    private Date content;
    private Date endDate;
    private int targetAmount;
    private int totalAmount;
    private String fundingStatus;
    private Date updateDate;
    private String planContent;
    private String budgetContent;
    private String infoContent;
    private int userCode;
    private String editStatus;
    private String judgeStatus;
    private String thumbnailFileName;
    private int sumPayAmount;
}
