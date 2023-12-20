package com.jellybears.krowdpoping.projectRegister.section01.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GoodsDTO {

    private int goodsCode;
    private String goodsName;
    private int quantity;
    private int projectCode; // project 참조키
    private int amount;

}
