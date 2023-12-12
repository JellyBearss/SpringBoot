package com.jellybears.krowdpoping.projectRegister.section02.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatorProfileDTO {
    private int profile_code;
    private String origin_name;
    private String update_name;
    private String register_date;
    private int delete_opt;
    private int user_code;
    private String file_type;
}
