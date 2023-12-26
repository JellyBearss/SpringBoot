package com.jellybears.krowdpoping.projectList.controller;

import com.jellybears.krowdpoping.projectList.model.dto.ProjectDTO;
import com.jellybears.krowdpoping.projectList.model.dto.ProjectImageDTO;
import com.jellybears.krowdpoping.projectList.model.service.ProjectListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/projectList")
@Controller
public class ProjectListController {

    private final ProjectListService projectListService;
    @Autowired
    public ProjectListController(ProjectListService projectListService) {
        this.projectListService=projectListService;
    }

    @GetMapping("/projectList")
    public String goList(Model model, @ModelAttribute ProjectDTO projectDTO,@ModelAttribute ProjectImageDTO projectImageDTO) {

        List<ProjectDTO> projectList=projectListService.selectProjectList(projectDTO);
        projectImageDTO.setProjectCode(projectDTO.getProjectCode());
        List<ProjectImageDTO> projectImageList=projectListService.selectImage(projectImageDTO);
        System.out.println("------------------------------------------");
        System.out.println(projectList);
        System.out.println("===========================>>>" + projectImageList);
        if(projectImageList.size() > 0){
            for (int i = 0; i < projectList.size(); i++) {
                projectList.get(i).setThumbnailFileName(projectImageList.get(i).getSavedName());
            }
        }
        System.out.println("===========================>>>" + projectList);
        model.addAttribute("projectList",projectList);

        model.addAttribute("projectImageList",projectImageList);
        return "projectList/projectList";
    }






}


