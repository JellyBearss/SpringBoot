package com.jellybears.krowdpoping.kakaopay.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 이번 요청으로 취소된 금액
 */
@Getter
@Setter
@ToString
public class ApprovedCancelAmount {
    private int total; // 이번 요청으로 취소된 전체 금액
}
