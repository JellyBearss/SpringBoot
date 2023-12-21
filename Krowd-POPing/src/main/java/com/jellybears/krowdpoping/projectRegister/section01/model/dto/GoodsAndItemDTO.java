package com.jellybears.krowdpoping.projectRegister.section01.model.dto;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GoodsAndItemDTO {

    private GoodsDTO goodsDTO;

    private List<ItemDTO> itemDTO;
}
