package com.jellybears.krowdpoping.category.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("category")
public class CategoryController {

    @GetMapping("category-all")
    public String goCategory(){


        return "category/categorypage-all";
    }

    @GetMapping("category-acc")
    public String goCategoryAcc(){

        return "category/categorypage-acc";
    }


}
