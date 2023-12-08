package com.jellybears.krowdpoping.user.model.service;

import com.jellybears.krowdpoping.user.model.dao.UserMapper;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService implements UserDetailsService {

    private final UserMapper mapper;

    public AuthenticationService(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        log.info("");
        log.info("");
        log.info("[AuthenticationService]=============================start");
        log.info("[AuthenticationService] userId :" + id);

        UserDTO user = mapper.findByUserId(id);
        log.info("[AuthenticationService] user :" + user);

        if (user == null) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");

        }
        log.info("[AuthenticationService] ===================================================== end");
        return user;

    }
}
