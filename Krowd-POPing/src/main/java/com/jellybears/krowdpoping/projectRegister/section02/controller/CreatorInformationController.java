package com.jellybears.krowdpoping.projectRegister.section02.controller;

import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CBusinessDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CPersonalDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.service.CreatorService;
import com.jellybears.krowdpoping.projectRegister.section02.model.service.FileService;
import com.jellybears.krowdpoping.user.model.dto.RoleTypeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Controller
@RequestMapping("/projectReg")
public class CreatorInformationController {

    private final CreatorService creatorService;
    private final FileService fileService;

    @GetMapping("/creator")
    public String goCreator(){
        return "creatorInfo/CreatorInfo2";
    }

    @PostMapping("/creatorRegSeller") // 사업자
    public String postSeller(Model model, CBusinessDTO cBusiness) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();
//
//        cBusiness.setUserCode(userCode);

        cBusiness.setUserCode(38);
//        creator = creatorService.getCreator(creator);
//        // 창작자 정보 없음
//        if(creator == null) {
//            creator = new CreatorDTO();
//            model.addAttribute("isNew", true);
//        } else { // 창작자 정보 있음
//            model.addAttribute("isNew", false);
//        }
        creatorService.insertCreator(cBusiness);
        System.out.println("cBusinessDTO =============== " + cBusiness);
        return "redirect:/projectReg/creator";
    }

    @PostMapping("/creatorRegUser") // 개인
    public String postUser(Model model, CPersonalDTO cPersonal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();
        cPersonal.setUserCode(userCode);
        creatorService.insertCreator(cPersonal);
        fileService.uploadFile(cPersonal.getFileInput(),userCode);
        System.out.println("cPersonal ============================ " + cPersonal);
        return "redirect:/projectReg/creator";
    }




//



}
