package com.jellybears.krowdpoping.projectManage.model.dao;

import com.jellybears.krowdpoping.projectManage.model.dto.ProjectManageDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectManageMapper {
    ProjectManageDTO selectProject(Long no);
}
