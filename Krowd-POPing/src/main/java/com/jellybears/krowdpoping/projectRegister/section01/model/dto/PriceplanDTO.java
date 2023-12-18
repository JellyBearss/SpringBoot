package com.jellybears.krowdpoping.projectRegister.section01.model.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PriceplanDTO {

    private int priceplanCode;
    private String priceplanName; // 요금제 타입명
    private String percent; // 퍼센트가 string?



}
