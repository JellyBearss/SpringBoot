package com.jellybears.krowdpoping.projectRegister.section02.controller;

import com.jellybears.krowdpoping.projectRegister.section02.model.dto.CreatorDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.service.CreatorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.PrintWriter;


@RequiredArgsConstructor
@Controller
@RequestMapping("/projectReg/*")
public class CreatorInformationController {

    private final CreatorService creatorService;

    /**
     * 창작자 정보 페이지 이동
     */
    @GetMapping("creator")
    public String goCreator(
        CreatorDTO creator
        , Model model
    ) throws  Exception{
        model.addAttribute("creator",creator);
        return "creatorInfo/CreatorInfo2";
    }

    @PostMapping("creatorReg")
    public String creatorReg(
            CreatorDTO creator
            , @RequestParam(value ="fileInput", required = true) MultipartFile fileInput
            , HttpServletRequest request
            , Model model
    ) throws  Exception{
        int userCode = creatorService.insertCreator(creator, fileInput, request);
        if(userCode>0){
            model.addAttribute("msg","정상적으로 등록되었습니다.");
            model.addAttribute("headSaveAt","Y");
        }else{
            model.addAttribute("msg","비정상 처리되었습니다.");
            model.addAttribute("headSaveAt","N");
        }
        model.addAttribute("creator",creator);
        return "creatorInfo/CreatorInfo2";
    }


    @PostMapping("creatorAccount")
    public String creatorAccount(
            CreatorDTO creator
            , @RequestParam(value ="businessAttach", required = true) MultipartFile businessAttach
            , HttpServletRequest request
            , Model model
    ) throws  Exception{
        int userCode = creatorService.updateCreator(creator, businessAttach, request);
        if(userCode>0){
            model.addAttribute("msg","세금계산서 정보 정상적으로 등록되었습니다.");
            model.addAttribute("headSaveAt","Y");
        }else{
            model.addAttribute("msg","세금계산서 정보 비정상 처리되었습니다.");
            model.addAttribute("headSaveAt","N");
        }
        model.addAttribute("creator",creator);
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

    /*
     * 서브컨텐츠 복사
     */
}
