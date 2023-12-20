package com.jellybears.krowdpoping.projectList.model.service;

import com.jellybears.krowdpoping.projectList.model.dao.ProjectListMapper;
import com.jellybears.krowdpoping.projectList.model.dto.ProjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectListService {

    private final ProjectListMapper projectListMapper;

    public ProjectListService(ProjectListMapper projectListMapper) {
        this.projectListMapper=projectListMapper;
    }

    public List<ProjectDTO> selectProjectList(ProjectDTO projectDTO) {
            return projectListMapper.selectProjectList(projectDTO);
    }
}
