package com.jellybears.krowdpoping.projectRegister.section02.controller;

import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.service.CreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Controller
@RequestMapping("/projectReg/*")
public class CreatorInformationController {

    private final CreatorService creatorService;

    /**
     * 창작자 정보 페이지 이동
     */
    @GetMapping("creator")
    public String goCreator() {
        return "creatorInfo/CreatorInfo2";
    }

//    @GetMapping("creator")
//    public String goCreator(Model model) {
//        model.addAttribute("creator", creatorService.getCreator());
//        return "creatorInfo/CreatorInfo2";
//    }



//    @GetMapping("insertCreator1")
//    public String setCreatorPage() {
//        return "creatorInfo/TestInsertCreator";
//    }
//
//    @PostMapping("insertCreator")
//    public String setCreator(CreatorDTO dto) {
//        creatorService.setCreator(dto);
//        return "creatorInfo/TestInsertCreator";
//    }


}
