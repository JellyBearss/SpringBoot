package com.jellybears.krowdpoping.projectRegister.section02.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatorDTO {

    private String creatorNm; // 창작자 프로필명
    private Integer crType; // 창작자 유형
    private String introduce; // 소개
    private String inquiryEmail; // 문의 이메일
    private String email; // user의 email 가져오기
    private String phone; // 전화번호
    private int issueType; // 발행 여부(개인/개인 사업자)
    private String name;  // user의 name 가져오기
    private String address; // 주소
    private int userCode;



}
