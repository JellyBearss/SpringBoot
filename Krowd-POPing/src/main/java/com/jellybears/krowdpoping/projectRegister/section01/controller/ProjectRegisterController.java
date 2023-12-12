package com.jellybears.krowdpoping.projectRegister.section01.controller;

import com.jellybears.krowdpoping.projectRegister.section01.model.dto.ProjectDTO;
import com.jellybears.krowdpoping.projectRegister.section01.model.service.ProjectRegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projectReg/*")
public class ProjectRegisterController {

    private final ProjectRegisterService registerService;

    public ProjectRegisterController(ProjectRegisterService registerService) {
        this.registerService = registerService;
    }


    @GetMapping("test")
    public String testIndex(){

        return "/projectRegister/textIndex";
    }



    /**
     * 프로젝트 등록 페이지 이동
     */
    @GetMapping("project")
    public String projectRegister() {
        System.out.println("확인!!!!!!!!");
        return "/projectRegister/projectReg1";
    }

    // 등록
    @PostMapping("project")
    public String registProjectInfo(@ModelAttribute ProjectDTO project, @RequestParam String[] tag){

        System.out.println("project = " + project);
        registerService.registProjectInfo(project);


        return "/projectRegister/testIndex";
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


}
