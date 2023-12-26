package com.jellybears.krowdpoping.projectRegister.section02.controller;

import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CBusinessDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CPersonalDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.service.CreatorService;
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


    @GetMapping("/creator")
    public String goCreator(){
        return "creatorInfo/CreatorInfo2";
    }

    @PostMapping("/creatorRegSeller") // 사업자
    public String postSeller(Model model, CBusinessDTO cBusiness) {
//      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        // AuthenticationService에서 반환하는 UserDetails를 가져옴
//        UserDetails userDetails = authenticationService.loadUserByUsername(authentication.getName());
//
//        if (userDetails instanceof RoleTypeDTO) {
//            // RoleTypeDTO인 경우 UserDTO를 추출
//            UserDTO loggedInUser = ((RoleTypeDTO) userDetails).getUserDTO();
//
//            CBusinessDTO cBusinessDTO = new CBusinessDTO();
//            cBusinessDTO.setUserCode(loggedInUser.getUser_code());
//        }
//
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();

        cBusiness.setUserCode(userCode);


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
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = authenticationService.loadUserByUsername(authentication.getName());

//        if (userDetails instanceof RoleTypeDTO) {
//            // RoleTypeDTO인 경우 UserDTO를 추출
//            UserDTO loggedInUser = ((RoleTypeDTO) userDetails).getUserDTO();
//
//            CPersonalDTO cPersonalDTO = new CPersonalDTO();
//            cPersonalDTO.setUserCode(loggedInUser.getUser_code());
//        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userCode = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO().getUser_code();
        cPersonal.setUserCode(userCode);
        System.out.println("userCode ======================= " + userCode);
        creatorService.insertCreator(cPersonal);
        System.out.println("cPersonal ============================ " + cPersonal);
        return "redirect:/projectReg/creator";
    }




//



}
