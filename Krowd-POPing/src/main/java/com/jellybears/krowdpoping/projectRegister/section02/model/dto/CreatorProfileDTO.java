package com.jellybears.krowdpoping.projectRegister.section02.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatorProfileDTO { // creator_files
    private int profileCode;
    private String originName;
    private String updateName;
    private String registerDate;
    private int deleteOpt;
    private int userCode;
}
