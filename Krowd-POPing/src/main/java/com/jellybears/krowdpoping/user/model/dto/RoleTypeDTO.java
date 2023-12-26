package com.jellybears.krowdpoping.user.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jellybears.krowdpoping.funding_process.model.dto.AddressDTO;
import com.jellybears.krowdpoping.funding_process.model.dto.ProductDTO;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
    private AddressDTO addressDTO;
    private AddressDTO defaultAddress;
    private ProductDTO productDTO;


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for(String role : roleDTO.getRole_name()) {
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
        return userDTO.getUserId();
    }



    public AddressDTO getDefaultAddress() {
        return defaultAddress;
    }

    // 새로 추가한 메서드
    public void setDefaultAddress(AddressDTO defaultAddress) {
        this.defaultAddress = defaultAddress;
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

    // 탈퇴 계정 여부 표현 ,비밀번호 만료 여부
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
