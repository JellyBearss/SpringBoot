package com.jellybears.krowdpoping.user.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString

public class RoleDTO {
    private int role_code;
    private String role_name;

    public int getRole_code() {
        return role_code;
    }

    public void setRole_code(int role_code) {
        this.role_code = role_code;
    }


    //    public String getRole_name() {
//        return role_name;
//    }

/* 다중 권한이 있는 유저가 있기 때문에*/
    public List<String> getRole_name() {
        if (this.role_name.length() > 0) {
            return Arrays.asList(this.role_name.split(","));
        }
        return new ArrayList<>();
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
