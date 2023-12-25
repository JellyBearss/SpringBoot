package com.jellybears.krowdpoping.projectRegister.section02.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PolicyDTO {
    private String basicPolicy;
    private int policyCode;
    private String difficultyInfo;
    private String productType;
    private String productName;
    private String size;
    private String material;
    private String specification;
    private String caution;
    private int fileCode;
    private int projectCode;


}
