package com.jellybears.krowdpoping.projectRegister.section01.model.dao;


import com.jellybears.krowdpoping.category.model.dto.CategoryDTO;
import com.jellybears.krowdpoping.projectRegister.section01.model.dto.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Update("UPDATE project SET priceplan_code = #{ priceplanCode } WHERE project_code = #{ projectCode }")
    int updatePriceplanRegister(int priceplanCode, Integer projectCode);

    @Select("SELECT priceplan_code\n" +
            "        FROM project\n" +
            "        WHERE project_code = #{ projectCode }")
    int selectPricePlanRegByProjectCode(Integer projectCode);

    int insertGoods(GoodsDTO goodsDTO);

    int insertItem(ItemDTO item);


    int insertItemQuantity(int goodsCode, int itemCode, int itemQuantity);

    List<GoodsAndItemDTO> selectGoodsRegByProjectCode(Integer projectCode);
}
