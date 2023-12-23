package com.jellybears.krowdpoping.funding_process.model.dto;

import com.jellybears.krowdpoping.project.model.dto.DetailGoodsDTO;
import com.jellybears.krowdpoping.projectRegister.section01.model.dto.GoodsDTO;
import lombok.*;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {
    private int user_code;
    private int goodsCode;
    private int deliveryCode;
    private String payApprovalCode;
    private int totalAmount;
    private Date payedTime;
    private DetailGoodsDTO detailGoodsDTO;
    private AddressDTO addressDTO;

    public void generateRandomValues() {
        // PayApprovalCode를 랜덤으로 생성하는 코드
        this.payApprovalCode = generateRandomPayApprovalCode();


        // PayedTime을 현재 날짜와 시간으로 설정
        this.payedTime = new Date();
    }

    private String generateRandomPayApprovalCode() {
        UUID uuid = UUID.randomUUID();

        // UUID에서 하이픈 제거하고, 문자열 길이를 8로 제한
        String randomCode = uuid.toString().replace("-", "").substring(0, 8);

        return randomCode;
    }
}
