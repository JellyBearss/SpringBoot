package com.jellybears.krowdpoping.projectRegister.section02.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CPersonalDTO extends CreatorDTO{
    private String identiNum1; // 주민등록번호 앞자리
    private String identiNum2; // 주민등록번호 뒷자리
    private String name;

}
