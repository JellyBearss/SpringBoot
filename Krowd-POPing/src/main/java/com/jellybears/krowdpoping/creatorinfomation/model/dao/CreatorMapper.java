package com.jellybears.krowdpoping.creatorinfomation.model.dao;

import com.jellybears.krowdpoping.creatorinfomation.model.dto.CreatorDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CreatorMapper {

    CreatorDTO selectCreator();
}
