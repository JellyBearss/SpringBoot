package com.jellybears.krowdpoping.category.controller;

import com.jellybears.krowdpoping.category.model.dto.CategoryDTO;
import com.jellybears.krowdpoping.category.model.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("category/*")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("category-all")
    public String goCategory(){


        return "/category/categorypage1-all";
    }

    @GetMapping("category-acc")
    public String goCategoryAcc(){

        return "/category/categorypage2-acc";
    }

    @GetMapping("category-digit")
    public String goCategoryDigit(){

        return "/category/categorypage3-digit";
    }


    @GetMapping("category-doll")
    public String goCategoryDoll(){

        return "/category/categorypage4-doll";
    }

    @GetMapping("category-stationery")
    public String goCategoryStationery(){

        return "/category/categorypage5-stationery";
    }

    @GetMapping("category-fancyGoods")
    public String goCategoryFancyGoods(){

        return "/category/categorypage6-fancyGoods";
    }

    @GetMapping("category-apparel")
    public String goCategoryApparel(){

        return "/category/categorypage7-apparel";
    }

    @GetMapping("category-etc")
    public String goCategoryEtc(){

        return "/category/categorypage8-etc";
    }


//    @GetMapping("list")
//    public String goCategoryList(){
//
//        List<CategoryDTO> categoryList = categoryService.AllCategoryList();
//
//        return "/category/list";
//    }

}
