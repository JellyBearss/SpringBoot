package com.jellybears.krowdpoping.projectRegister.section02.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PolicyDTO {
    private int policyCode;
    private int fileCode;
    private int projectCode;
    private String basicPolicy;
    private String difficultyInfo;
    private String productType;
    private int productName;
    private String size;
    private String material;
    private String specification;
    private String caution;
}
