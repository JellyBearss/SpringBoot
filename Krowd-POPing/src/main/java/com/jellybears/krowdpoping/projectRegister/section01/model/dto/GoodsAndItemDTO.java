package com.jellybears.krowdpoping.projectRegister.section01.model.dto;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GoodsAndItemDTO {

//    private GoodsDTO goodsDTO;
    private int goodsCode;
    private String goodsName;
    private int quantity;
    private int projectCode; // project 참조키
    private int amount;
    private List<ItemDTO> itemDTO;

}
