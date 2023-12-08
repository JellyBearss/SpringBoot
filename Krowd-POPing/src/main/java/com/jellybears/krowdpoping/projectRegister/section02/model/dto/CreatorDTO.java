package com.jellybears.krowdpoping.projectRegister.section02.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatorDTO {

    private String crType;
    private String crBusinessNum;
    private String phone;
    private String email;
    private String address;
    private String introduce;

    @Builder
    public CreatorDTO(String crType, String crBusinessNum, String phone, String email, String address, String introduce) {
        this.crType = crType;
        this.crBusinessNum = crBusinessNum;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.introduce = introduce;
    }

}
