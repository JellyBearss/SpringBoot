package com.jellybears.krowdpoping.project.model.service;

import com.jellybears.krowdpoping.project.model.dao.ProjectMapper;
import com.jellybears.krowdpoping.project.model.dto.DetailGoodsDTO;
import com.jellybears.krowdpoping.project.model.dto.DetailGoodsDetailDTO;
import com.jellybears.krowdpoping.project.model.dto.DetailItemDTO;
import com.jellybears.krowdpoping.project.model.dto.DetailProjectDTO;
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
}
