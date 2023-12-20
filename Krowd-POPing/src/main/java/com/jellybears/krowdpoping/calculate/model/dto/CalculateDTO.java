package com.jellybears.krowdpoping.calculate.model.dto;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CalculateDTO {

    private String title;  // 프로젝트명
    private String creatorNm;  //창작자 프로필 이름
    private String name;   // 창작자 정보 - 이름
    private String businessNum;   // 사업자 등록번호
    private Timestamp calDate;   // 정산일
    private int sumPayAmountCount;   // 총 모금액 명 수
    private int sumPayAmount;   // 총 모금액
    private int cancelCount;   // 결제 취소 명 수
    private int cancellationAmount;   // 결제 취소 금액
    private int payCount;   // 결제 금액 명 수 (sumPayAmountCount-cancelCount)
    private int pay;        // 결제 금액(sumPayAmount-cancellationAmount)
    private String type;   // 요금제 유형
    private String percent;   // 요금제 퍼센트
    private int krowdFee;   // popping 수수료 (pay * percent/100)
    private int entertainmentFee;   // 소속사 수수료 (pay * 0.02)
    private int sumFee;         // 수수료 총계 (krowdFee+entertainmentFee)
    private int totalMoney;     // 정산 금액(pay-sumFee)




}


