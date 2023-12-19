package com.jellybears.krowdpoping.project.model.dao;

import com.jellybears.krowdpoping.project.model.dto.DetailGoodsDTO;
import com.jellybears.krowdpoping.project.model.dto.DetailGoodsDetailDTO;
import com.jellybears.krowdpoping.project.model.dto.DetailItemDTO;
import com.jellybears.krowdpoping.project.model.dto.DetailProjectDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    DetailProjectDTO goProjectDetail(Long no);


}
