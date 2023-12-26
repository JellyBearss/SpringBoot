package com.jellybears.krowdpoping.admin.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminGoodsDTO {

    private int goodsCode;
    private String goodsName;
    private int quantity;
    private int projectCode;
    private int amount;
}
