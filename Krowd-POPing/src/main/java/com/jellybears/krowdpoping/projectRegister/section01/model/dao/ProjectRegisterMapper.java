package com.jellybears.krowdpoping.projectRegister.section01.model.dao;


import com.jellybears.krowdpoping.category.model.dto.CategoryDTO;
import com.jellybears.krowdpoping.projectRegister.section01.model.dto.InfoDTO;
import com.jellybears.krowdpoping.projectRegister.section01.model.dto.PlanDTO;
import com.jellybears.krowdpoping.projectRegister.section01.model.dto.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectRegisterMapper {

    int updatePlanRegister(PlanDTO plan);

    Integer getEditProjectCode(int userCode);

    void UpdateprojectRegister(ProjectDTO project);

    void insertProjectRegister(ProjectDTO project);

    //
    ProjectDTO selectProjectRegByProjectCode(Integer projectCode);

    PlanDTO selectPlanRegByProjectCode(Integer projectCode);

    int updateInfoRegister(InfoDTO infoDTO);

    InfoDTO selectInfoRegByProjectCode(Integer projectCode);

    List<CategoryDTO> getSubCategoryList();
}
