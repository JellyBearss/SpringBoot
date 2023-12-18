package com.jellybears.krowdpoping.project.model.dao;

import com.jellybears.krowdpoping.project.model.dto.DetailProjectDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper {
    DetailProjectDTO goProjectDetail(Long no);
}
