package com.jellybears.krowdpoping.projectRegister.section02.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatorDTO {

    private String creatorNm;
    private int crType;
    private String businessNum;
    private String address;
    int userCode;
    private String phone;
    private String email;
    private String introduce;
    private String identiNum;
    private String accUserNm;
    private String ceoNm;
    private int issueType;

    private boolean isNew;

    @Builder
    public CreatorDTO(int crType,String businessNum,String address,int userCode,String phone,String email,String introduce
            , String identiNum, String accUserNm, String ceoNm, int issueType, String creatorNm) {
        this.crType=crType;
        this.businessNum=businessNum;
        this.address=address;
        this.userCode=userCode;
        this.phone=phone;
        this.email=email;
        this.introduce=introduce;
        this.identiNum=identiNum;
        this.accUserNm=accUserNm;
        this.ceoNm=ceoNm;
        this.issueType=issueType;
        this.creatorNm=creatorNm;
    }
}
