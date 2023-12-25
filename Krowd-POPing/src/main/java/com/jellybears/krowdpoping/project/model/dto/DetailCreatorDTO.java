package com.jellybears.krowdpoping.project.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetailCreatorDTO {
    private int CreatorUserCode; //창작자 유저코드
    private String creatorNm; //창작자 별칭
    private String introduce; //창작자 소개
    private String inquiryEmail; //창작자 이메일


}
