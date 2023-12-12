package com.jellybears.krowdpoping.projectRegister.section02.model.dao;

import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorProfileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CreatorMapper {

    String selectCreator();
    int insertCreator(CreatorDTO vo);
    int insertCreatorProfile(CreatorProfileDTO vo);

    int deleteCreatorProfile(CreatorProfileDTO vo);
    int deleteCreator(CreatorDTO vo);

    int updateCreator(CreatorDTO vo);

//    int insertCreator(CreatorDTO creatorDTO);
}
