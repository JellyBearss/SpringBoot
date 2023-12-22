package com.jellybears.krowdpoping.admin.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminGoodsDTO {

    private int goodsCode;
    private String name;
    private int quantity;
    private int projectCode;
    private int amount;
}
