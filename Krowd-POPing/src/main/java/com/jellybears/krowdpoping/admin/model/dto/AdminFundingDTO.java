package com.jellybears.krowdpoping.admin.model.dto;

import com.jellybears.krowdpoping.category.model.dto.CategoryDTO;
import com.jellybears.krowdpoping.projectRegister.section01.model.dto.GoodsDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorDTO;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import lombok.*;

import java.math.BigInteger;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AdminFundingDTO {

    private int projectCode;            // 프로젝트코드 (PK)
    private String title;               // 프로젝트명
    private String content;             // 프로젝트소개
    private Date startDate;             // 펀딩시작일
    private Date endDate;               // 펀딩종료일
    private BigInteger targetAmount;    // 목표금액
    private BigInteger totalAmount;     // 최종금액
    private String fundingStatus;       // 펀딩상태
    private Date updateDate;            // 수정일자
    private String planContent;         // 프로젝트일정계획
    private String budgetContent;       // 프로젝트예산계획
    private String infoContent;         // 프로젝트정보
    private String editStatus;          // 편집상태
    private BigInteger sumPayAmount;    // 총금액
    private String judgeStatus;         // 심사상태

    private int user_code;              // 회원코드 (FK)
    private int categoryCode;           // 카테고리코드 (FK)
    private int pricePlanCode;          // 예산계획코드(FK)

    private UserDTO userDTO;
    private String userId;              // 회원아이디

    private CategoryDTO categoryDTO;
    private String categoryTitle;       // 카테고리목록

    private AdminCreatorDTO adminCreatorDTO;
    // private String accUserNm;         // 세금계산서 발행 성명(상호명)

    private GoodsDTO goodsDTO;

    private String f_acceptor;          // 승인자 아이디
    private String f_confirmdate;       // 승인 날짜

}
