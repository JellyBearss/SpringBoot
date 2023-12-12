package com.jellybears.krowdpoping.category.model.dao;

import com.jellybears.krowdpoping.category.model.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryDTO> AllCategoryList();
}
