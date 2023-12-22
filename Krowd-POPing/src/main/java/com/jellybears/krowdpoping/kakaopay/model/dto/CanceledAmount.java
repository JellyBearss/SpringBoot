package com.jellybears.krowdpoping.kakaopay.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 누계 취소 금액
 */
@Getter
@Setter
@ToString
public class CanceledAmount {
    private int total; // 취소된 전체 누적 금액
}
