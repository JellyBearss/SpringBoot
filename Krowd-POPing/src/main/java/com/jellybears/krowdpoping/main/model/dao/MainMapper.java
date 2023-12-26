package com.jellybears.krowdpoping.main.model.dao;

import com.jellybears.krowdpoping.category.model.dto.CaProjectDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {
    List<CaProjectDTO> getMainAllProjectList();

    List<CaProjectDTO> getMainDeadlineProjectList();
}
