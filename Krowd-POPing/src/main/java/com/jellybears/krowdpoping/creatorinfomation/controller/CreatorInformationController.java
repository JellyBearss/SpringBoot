package com.jellybears.krowdpoping.creatorinfomation.controller;

import com.jellybears.krowdpoping.creatorinfomation.model.service.CreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class CreatorInformationController {

    private final CreatorService creatorService;

    @GetMapping("creator")
    public String goCreator(Model model) {
        model.addAttribute("creator", creatorService.getCreator());
        return "creatorInfo/CreatorInfo2";
    }

}
