package com.jellybears.krowdpoping.project.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetailItemDTO {

    private int itemCode;
    private String itemName;
    private DetailGoodsDetailDTO goodsDetail;


}
