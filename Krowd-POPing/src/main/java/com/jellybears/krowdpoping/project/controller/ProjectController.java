package com.jellybears.krowdpoping.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("projectdetail")
public class ProjectController {
    @GetMapping("projectdetail")
    public String goProjectDetail(){

        return "project/projectpage3";
    }

}
