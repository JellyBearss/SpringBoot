package com.jellybears.krowdpoping.project.model.service;

import com.jellybears.krowdpoping.project.model.dao.ProjectMapper;
import com.jellybears.krowdpoping.project.model.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    public DetailProjectDTO goProjectDetail(Long no) {
        return projectMapper.goProjectDetail(no);
    }

    @Override
    public DetailGoodsDTO getGoodsDetails(int goodsCode) {
        return projectMapper.getGoodsDetails(goodsCode);
    }

    //프로젝트 파일 전달용
    @Override
    public DetailProjectImageDTO getProjectFile(Long no) {
        return projectMapper.getProjectFile(no);
    }
}
