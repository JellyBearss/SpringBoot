package com.jellybears.krowdpoping.projectRegister.section01.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InfoDTO {

    private int projectCode;
    private String planContent;
    private String budgetContent;
    private String infoContent;

    private int userCode;

}
