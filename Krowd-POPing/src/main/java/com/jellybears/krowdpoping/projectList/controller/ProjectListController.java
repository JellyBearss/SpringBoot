package com.jellybears.krowdpoping.projectList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@RequestMapping("/projectList/*")
@Controller
@SessionAttributes("list")
public class ProjectListController {

    @GetMapping("projectList")
    public String goList() {
        return "projectList/projectList";
    }




}
