package com.jellybears.krowdpoping.projectRegister.section02.model.dao;

import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CBusinessDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CPersonalDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CreatorMapper {

    CreatorDTO selectCreator(CreatorDTO vo);

    int insertBusiness(CBusinessDTO cBusinessDTO);
    int insertPersonal(CPersonalDTO cPersonalDTO);

    int deleteCreator(CreatorDTO vo);

    int updateCreator(CreatorDTO vo);





//    int insertCreator(CreatorDTO creatorDTO);
}
