package com.jellybears.krowdpoping.projectRegister.section01.model.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PlanDTO {

    private int projectCode;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private int targetAmount;
    private int totalAmount;

}
