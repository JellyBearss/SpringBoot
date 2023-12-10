package com.jellybears.krowdpoping.user.model.service;

import com.jellybears.krowdpoping.common.exception.member.UserModifyException;
import com.jellybears.krowdpoping.common.exception.member.UserRegistException;
import com.jellybears.krowdpoping.user.model.dao.UserMapper;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//회원 정보 수정 추가 예정
//회원 삭제 추가 예정
@Service
@Slf4j
public class UserServiceImpl implements UserService{
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserMapper mapper) {
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;

    }
    @Override
    public boolean selectUserById(String userId) {
        String result = mapper.selectUserById(userId);
        return result !=null? true : false;
    }
//    @Override
//    @Transactional
//    public void registUser(UserDTO user) throws UserRegistException {
//        log.info("[UserService Insert User : " + user);
//        int result = mapper.insertUser(user);
//
//        log.info("[UserService] Insert result : " + ((result > 0) ? "회원가입 성공 " : "회원가입 실패"));
//
//        if (!(result > 0)) {
//            throw new UserRegistException("회원 가입에 실패하였습니다.");
//        }
//    }



    }

