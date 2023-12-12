package com.jellybears.krowdpoping.projectRegister.section01.model.dao;


import com.jellybears.krowdpoping.projectRegister.section01.model.dto.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectRegisterMapper {
    int registProjectInfo(ProjectDTO project);


}
