package com.jellybears.krowdpoping.projectRegister.section02.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PolicyDTO {
    private String basic_policy;
    private int policy_code;
    private String difficulty_info;
    private String product_type;
    private String product_name;
    private String size;
    private String material;
    private String specification;
    private String caution;
    private int file_code;

    @Builder
    public PolicyDTO(String basic_policy,int policy_code,String difficulty_info,String product_type,String product_name,
                     String size,String material,String specification,String caution,int file_code) {
        this.basic_policy=basic_policy;
        this.policy_code=policy_code;
        this.difficulty_info=difficulty_info;
        this.product_type=product_type;
        this.product_name=product_name;
        this.size=size;
        this.material=material;
        this.specification=specification;
        this.caution=caution;
        this.file_code=file_code;
    }

}
