package com.jellybears.krowdpoping.user.model.dao;

import com.jellybears.krowdpoping.user.model.dto.RoleTypeDTO;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    String selectUserById(String userId);
    int insertUser(UserDTO user);
    String selectEncryptedPwd(UserDTO user);
    int updateUser(UserDTO user);
    RoleTypeDTO findByUserId(String userId);
    UserDTO selectUser(UserDTO user);


}
