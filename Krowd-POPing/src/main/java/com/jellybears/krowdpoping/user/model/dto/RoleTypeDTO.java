package com.jellybears.krowdpoping.user.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RoleTypeDTO implements UserDetails {
    private RoleDTO roleDTO;
    private UserDTO userDTO;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for(String role : roleDTO.getRole_name().split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }

        return roles;
    }
    @Override
    public String getPassword() {
        return userDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDTO.getName();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true; // 만료되지 않음
    }

    // 계정 잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        return true; // 잠기지 않음
    }

    // 비밀번호 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 만료되지 않음
    }

    // 계정 활성화 여부
    @Override
    public boolean isEnabled() {
        return true; // 활성화
    }


}
