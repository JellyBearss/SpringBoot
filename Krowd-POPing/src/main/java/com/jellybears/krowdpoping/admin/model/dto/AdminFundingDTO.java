package com.jellybears.krowdpoping.admin.model.dto;

import lombok.*;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminFundingDTO {

    private int projectCode;            // (PK)
    private String title;
    private int categoryCode;           // (FK)
    private String content;
    private Timestamp startDate;
    private Timestamp endDate;
    private BigInteger targetAmount;
    private BigInteger totalAmount;
    private String fundingStatus;
    private Date updateDate;
    private String planContent;
    private String budgetContent;
    private String infoContent;
    private int userCode;               // (FK)
    private String editStatus;
    private BigInteger sumPayAmount;
    private String judgeStatus;
    private int pricePlanCode;          // (FK)

}
