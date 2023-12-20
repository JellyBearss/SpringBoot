package com.jellybears.krowdpoping.user.model.dao;

import com.jellybears.krowdpoping.user.model.dto.EmailDTO;
import com.jellybears.krowdpoping.user.model.dto.EmailandUserDTO;
import com.jellybears.krowdpoping.user.model.dto.RoleTypeDTO;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserDTO selectUserById(String userId);
    int insertUser(UserDTO user);
    int selectLastInsertUserCode();
    int insertRoletype(int userCode);

    String selectEncryptedPwd(UserDTO user);
    int updateUser(UserDTO user);
    RoleTypeDTO findByUserId(String userId);
    UserDTO selectUser(UserDTO user);
    int deleteUser(UserDTO user);



}
