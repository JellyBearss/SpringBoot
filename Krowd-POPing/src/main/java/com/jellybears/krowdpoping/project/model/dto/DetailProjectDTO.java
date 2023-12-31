package com.jellybears.krowdpoping.project.model.dto;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetailProjectDTO {

    private int projectCode; //프로젝트 코드
    private String title; //프로젝트 제목
    private String content; //간단 소개
    private Date startDate; //시작일
    private Date endDate; //종료일
    private Long targetAmount; //목표 금액
    private String planContent; //일정
    private String budgetContent; //예산
    private String infoContent; //소개
    private DetailCategoryDTO category; //카테고리
    private List<DetailGoodsDTO> goods; //굿즈디테일DTO와 아이템DTO 에 대한 것까지 모두 담아준다.
    private DetailCreatorDTO creator; //창작자 정보
    private int userCode;
//    private DetailProjectImageDTO image; // 대표이미지 불러오기
    private Object productDTO;
    private int sumPayAmount;
    private double achievement;
}
