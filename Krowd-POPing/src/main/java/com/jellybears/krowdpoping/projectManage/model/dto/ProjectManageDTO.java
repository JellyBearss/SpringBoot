package com.jellybears.krowdpoping.projectManage.model.dto;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectManageDTO {
    private String title;
    private Timestamp startDate;
    private Timestamp endDate;
    private int sumPayMoney;
    private int achieveRate;
    private int spCount;
    private int leftDate;
}
