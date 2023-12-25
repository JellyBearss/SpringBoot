package com.jellybears.krowdpoping.project.model.service;

import com.jellybears.krowdpoping.project.model.dto.*;

import java.util.List;

public interface ProjectService {
    DetailProjectDTO goProjectDetail(Long no);

    DetailGoodsDTO getGoodsDetails(int goodsCode);

    DetailProjectImageDTO getProjectFile(Long no);
}
