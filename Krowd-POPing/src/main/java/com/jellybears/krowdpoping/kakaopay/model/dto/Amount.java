package com.jellybears.krowdpoping.kakaopay.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 결제 금액 정보
 */
@Getter
@Setter
@ToString
public class Amount {
    private int total; // 총 결제 금액
}
