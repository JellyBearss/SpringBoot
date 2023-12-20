package com.jellybears.krowdpoping.projectManage.model.service;

import com.jellybears.krowdpoping.projectManage.model.dao.ProjectManageMapper;
import com.jellybears.krowdpoping.projectManage.model.dto.ProjectManageDTO;
import org.springframework.stereotype.Service;

@Service
public class ProjectManageService {
    private final ProjectManageMapper projectManageMapper;

    public ProjectManageService(ProjectManageMapper projectManageMapper){this.projectManageMapper=projectManageMapper;}

    public ProjectManageDTO selectProject(Long no) {return projectManageMapper.selectProject(no);}
}
