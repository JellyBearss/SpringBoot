package com.jellybears.krowdpoping.kakaopay.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 취소 요청 시 전달한 값
 */
@Getter
@Setter
@ToString
public class CancelAvailableAmount {
    private int total; // 전체 취소 가능 금액
}
