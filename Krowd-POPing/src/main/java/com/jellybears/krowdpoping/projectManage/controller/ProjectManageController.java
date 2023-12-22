package com.jellybears.krowdpoping.projectManage.controller;

import com.jellybears.krowdpoping.projectManage.model.dto.ProjectManageDTO;
import com.jellybears.krowdpoping.projectManage.model.service.ProjectManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/projectList")
@Controller
public class ProjectManageController {

    private final ProjectManageService projectManageService;

    @Autowired
    public ProjectManageController(ProjectManageService projectManageService) {this.projectManageService=projectManageService;}

    @GetMapping("/projectManage")
    public String goManage(@RequestParam(name="no") Long no, Model model) {

        ProjectManageDTO manage=projectManageService.selectProject(no);
        model.addAttribute("manage",manage);
        System.out.println("manage = " + manage);
        return "projectManage/projectManage";
    }

}
