package com.jellybears.krowdpoping.project.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetailGoodsDTO {

    private int goodsCode;
    private String goodsName;
    private int quantity;
    private int projectCode;
    private int amount;
//    private DetailGoodsDetailDTO goodsDetail;
    private List<DetailItemDTO> item;
}
