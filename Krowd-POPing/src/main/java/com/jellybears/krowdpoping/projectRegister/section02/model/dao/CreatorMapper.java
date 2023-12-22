package com.jellybears.krowdpoping.projectRegister.section02.model.dao;

import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorProfileDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CreatorMapper {

    CreatorDTO selectCreator(CreatorDTO vo);

    CreatorVO selectCreatorTmp(CreatorVO vo);

    int insertCreator(CreatorDTO vo);
    int insertCreatorProfile(CreatorProfileDTO vo);

    int deleteCreatorProfile(CreatorProfileDTO vo);
    int deleteCreator(CreatorDTO vo);

    int updateCreator(CreatorDTO vo);

    CreatorProfileDTO selectFilesInfo(CreatorProfileDTO vo);



//    int insertCreator(CreatorDTO creatorDTO);
}
