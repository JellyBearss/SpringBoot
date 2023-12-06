package com.jellybears.krowdpoping.creatorinfomation.controller;

import com.jellybears.krowdpoping.creatorinfomation.model.dto.CreatorDTO;
import com.jellybears.krowdpoping.creatorinfomation.model.service.CreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Controller
@RequestMapping("/creatorInfo/*")
public class CreatorInformationController {

    private final CreatorService creatorService;

    /**
     * 창작자 정보 페이지 이동
     * @param model
     * @return
     */
    @GetMapping("creator")
    public String goCreator(Model model) {
        model.addAttribute("creator", creatorService.getCreator());
        return "creatorInfo/CreatorInfo2";
    }


    @GetMapping("insertCreator1")
    public String setCreatorPage() {
        return "creatorInfo/TestInsertCreator";
    }

    @PostMapping("insertCreator")
    public String setCreator(CreatorDTO dto) {
        creatorService.setCreator(dto);
        return "creatorInfo/TestInsertCreator";
    }


}
