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

    //디지털 카테고리 전체 검색
    List<CaProjectDTO> getDigitProjectList();

    //디지털 카테고리 아이디에 따른 세부카테고리 검색
    List<CaProjectDTO> getSubDigitProjectListByCategoryId(Integer categoryId);

    //인형 카테고리 전체 검색
    List<CaProjectDTO> getDollProjectList();

    //인형 카테고리 아이디에 따른 세부카테고리 검색
    List<CaProjectDTO> getSubDollProjectListByCategoryId(Integer categoryId);

    //문구 카테고리 전체 검색
    List<CaProjectDTO> getStaProjectList();

    //문구 카테고리이 아이디에 따른 세부카테고리 검색
    List<CaProjectDTO> getSubStaProjectListByCategoryId(Integer categoryId);

    //잡화 카테고리 전체 검색
    List<CaProjectDTO> getFanProjectList();

    //잡화 카테고리 아이디에 따른 세부카테고리 검색
    List<CaProjectDTO> getSubFanProjectListByCategoryId(Integer categoryId);

    //의류 카테고리 전체 검색
    List<CaProjectDTO> getAppProjectList();

    //의류 카테고리 아이디에 따른 세부카테고리 검색
    List<CaProjectDTO> getSubAppProjectListByCategoryId(Integer categoryId);

    //기타 카테고리 전체 검색 : 기타는 세부 없음
    List<CaProjectDTO> getEtcProjectList();
}
