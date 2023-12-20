package com.jellybears.krowdpoping.projectRegister.section02.controller;

import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorProfileDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.service.CreatorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@Controller
@RequestMapping("/projectReg")
public class CreatorInformationController {

    private final CreatorService creatorService;

    /**
     * 창작자 정보 페이지 이동
     */
    @RequestMapping(value="/creator")
    public String goCreator(
        CreatorDTO creator
        , Model model
        , @RequestParam(value="headSaveAt", required = false) String headSaveAt
        , @RequestParam(value="msg", required = false) String msg
        , HttpSession session
    ) throws  Exception{
//        Integer userCode = (Integer) session.getAttribute("userCode");
        Integer userCode = 7;
        // 유저에 대해서 창작자 정보가 있는지 체크



        creator.setUserCode(userCode);
        creator = creatorService.getCreator(creator);
        // 창작자 정보 없음
        if(creator == null) {
            creator = new CreatorDTO();
            model.addAttribute("isNew", true);
        } else { // 창작자 정보 있음
            model.addAttribute("isNew", false);
        }
        //CreatorVO CreatorVO = new CreatorVO();
        //CreatorVO =creatorService.selectCreatorTmp(CreatorVO);
        CreatorProfileDTO profile = new CreatorProfileDTO();
        profile.setUserCode(userCode);
        profile.setFileType("profile");
        profile = creatorService.selectFilesInfo(profile);

        model.addAttribute("headSaveAt",headSaveAt);
        model.addAttribute("profile",profile);
        model.addAttribute("creator",creator);
        return "creatorInfo/CreatorInfo2";
    }

    @PostMapping("/creatorReg")
    public String creatorReg(
            CreatorDTO creator
            , @RequestParam(value ="fileInput", required = true) MultipartFile fileInput
            , HttpServletRequest request
            , Model model
    ) throws  Exception{
        int userCode = 0;
        if( creator.isNew()) {
            userCode = creatorService.insertCreator(creator, fileInput, request);
        } else {
            // update creator
            System.out.println("================================= update");
        }

        if(userCode>0){
            return "redirect:/projectReg/creator?headSaveAt=Y&msg=success";
        }else{
            return "redirect:/projectReg/creator?headSaveAt=N&msg=fail";
        }


    }


    @PostMapping("/creatorAccount")
    public String creatorAccount(
            CreatorDTO creator
            , @RequestParam(value ="p0_businessAttach", required = false) MultipartFile p0_businessAttach
            , @RequestParam(value ="p1_businessAttach", required = false) MultipartFile p1_businessAttach
            , HttpServletRequest request
            , Model model
    ) throws  Exception{
        int userCode = 0;
        if(creator.getIssueType() == 0){
            userCode = creatorService.updateCreator(creator, p0_businessAttach, request);
        }else{
            userCode = creatorService.updateCreator(creator, p1_businessAttach, request);
        }

        if(userCode>0){
            model.addAttribute("msg","세금계산서 정보 정상적으로 등록되었습니다.");
            model.addAttribute("detailSaveAt","Y");
        }else{
            model.addAttribute("msg","세금계산서 정보 비정상 처리되었습니다.");
            model.addAttribute("detailSaveAt","N");
        }
        model.addAttribute("creator",creator);
        return "creatorInfo/CreatorInfo2";
    }

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

    /*
     * 서브컨텐츠 복사
     */
}
