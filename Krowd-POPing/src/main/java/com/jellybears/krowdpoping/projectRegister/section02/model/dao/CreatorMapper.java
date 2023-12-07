package com.jellybears.krowdpoping.projectRegister.section02.model.dao;

import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CreatorMapper {

    String selectCreator();

//    int insertCreator(CreatorDTO creatorDTO);
}
