package com.jellybears.krowdpoping.projectRegister.section02.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatorDTO extends CreatorProfileDTO {

    private String cr_type;
    private String business_num;
    private String address;
    int user_code;
    private String phone;
    private String email;
    private String introduce;
    private String identi_num;
    private String acc_user_nm;
    private String ceo_nm;
    private int issue_type;

    @Builder
    public CreatorDTO(String cr_type,String business_num,String address,int user_code,String phone,String email,String introduce) {
        this.cr_type=cr_type;
        this.business_num=business_num;
        this.address=address;
        this.user_code=user_code;
        this.phone=phone;
        this.email=email;
        this.introduce=introduce;
    }
}
