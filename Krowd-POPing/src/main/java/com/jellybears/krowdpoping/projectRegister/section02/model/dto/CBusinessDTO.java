package com.jellybears.krowdpoping.projectRegister.section02.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CBusinessDTO extends CreatorDTO{


    private String accUserNm; // 상호명
    private String ceoNm; // 대표자명
    private String businessNum; // 사업자등록번호
}
