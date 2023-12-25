package com.jellybears.krowdpoping.main.controller;

import com.jellybears.krowdpoping.category.model.dto.CaProjectDTO;
import com.jellybears.krowdpoping.main.model.service.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("krowdpoping")
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("mainpage")

    public String goMain(Model model) {

//        List<CaProjectDTO> mainAllProjectList = mainService.getMainAllProjectList();
//        model.addAttribute("mainAllProjectList", mainAllProjectList);
//
//        System.out.println("mainAllProjectList = " + mainAllProjectList);

        return "main/main";
    }
}
