package com.jellybears.krowdpoping.projectRegister.section01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projectReg/*")
public class ProjectRegisterController {



    /**
     * 프로젝트 등록 페이지 이동
     */
    @GetMapping("project")
    public String projectRegister() {

        return "/projectRegister/projectReg1";
    }

    @GetMapping("pricing")
    public String priceRegister() {

        return "/projectRegister/projectReg3";
    }

    @GetMapping("planning")
    public String planRegister() {

        return "/projectRegister/projectReg4";
    }

    @GetMapping("info")
    public String infoRegister() {

        return "/projectRegister/projectReg5";
    }

    @GetMapping("goods")
    public String goodsRegister() {

        return "/projectRegister/projectReg6";
    }

//    @GetMapping("start")
//    public String startRegister() {
//
//        return "projectStart/projectStart";
//    }
//
//    @GetMapping("check")
//    public String startCheckRegister() {
//
//        return "projectStart/projectStartCheck";
//    }



}
