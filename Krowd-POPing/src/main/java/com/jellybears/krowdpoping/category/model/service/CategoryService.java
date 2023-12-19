package com.jellybears.krowdpoping.category.model.service;

import com.jellybears.krowdpoping.category.model.dao.CategoryMapper;
import com.jellybears.krowdpoping.category.model.dto.CaProjectDTO;
import com.jellybears.krowdpoping.category.model.dto.CategoryDTO;
import com.jellybears.krowdpoping.projectRegister.section01.model.dto.ProjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDTO> AllCategoryList() {

        return categoryMapper.AllCategoryList();
    }


    public List<CaProjectDTO> getAllProjectList() {

        return categoryMapper.AllProjectList();
    }
}
