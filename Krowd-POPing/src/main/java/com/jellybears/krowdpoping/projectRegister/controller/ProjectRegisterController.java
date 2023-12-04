package com.jellybears.krowdpoping.projectRegister.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projectRegister/*")
public class ProjectRegisterController {

    /**
     * 프로젝트 등록 페이지 이동
     */
    @GetMapping("register")
    public String goRegister() {

        return "/projectRegister/projectReg1";
    }
}
