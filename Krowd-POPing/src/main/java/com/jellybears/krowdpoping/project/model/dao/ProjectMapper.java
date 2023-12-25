package com.jellybears.krowdpoping.project.model.dao;

import com.jellybears.krowdpoping.project.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    DetailProjectDTO goProjectDetail(Long no);
    DetailGoodsDTO getGoodsDetails(int goodsCode);

    DetailProjectImageDTO getProjectFile(Long no);
}
