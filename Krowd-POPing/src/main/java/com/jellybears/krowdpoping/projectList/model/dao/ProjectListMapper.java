package com.jellybears.krowdpoping.projectList.model.dao;

import com.jellybears.krowdpoping.projectList.model.dto.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectListMapper {

    List<ProjectDTO> selectProjectList(ProjectDTO projectDTO);
}
