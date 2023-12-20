package com.jellybears.krowdpoping.category.model.dao;

import com.jellybears.krowdpoping.category.model.dto.CaProjectDTO;
import com.jellybears.krowdpoping.category.model.dto.CategoryDTO;
import com.jellybears.krowdpoping.projectRegister.section01.model.dto.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryDTO> AllCategoryList();

    //전체 프로젝트
    List<CaProjectDTO> AllProjectList();

    //액세서리 검색
    List<CaProjectDTO> AccProjectList();

    //액세서리의 카테고리 아이디에 따른 세부카테고리 검색
    List<CaProjectDTO> getSubProjectListByCategoryId(Integer categoryId);
}
