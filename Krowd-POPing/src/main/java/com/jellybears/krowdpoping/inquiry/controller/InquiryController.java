package com.jellybears.krowdpoping.inquiry.controller;

import com.jellybears.krowdpoping.inquiry.model.dto.InquiryDTO;
import com.jellybears.krowdpoping.inquiry.model.service.InquiryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("inquiry/*")
@Slf4j
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("inquiry")
    public String goInguiary(){

        return "/mypage/MYP_inquiry";
    }

    @GetMapping("inquiryContent")
    public String goInguiaryContent(){

        return "/mypage/MYP_inquiryContent";
    }

    @GetMapping("inquiryForm")
    public String goInguiaryForm(){

        return "/inquiry/inquiryForm";
    }


    @GetMapping("save")
    public String saveForm() {
        return "inquiry/save";
    }

    @PostMapping("save")
    @ResponseBody
    public String save(@ModelAttribute InquiryDTO inquiry){ //()여기에 DTO에 담은 것을 넘겨준다.
        System.out.println("inquiry = " + inquiry);
    return "";

    }

    @GetMapping("list")
    public String findInquiryList(Model model){

        List<InquiryDTO> inquiryList = inquiryService.findInquiryList();
        log.info("list {}", inquiryList);

        model.addAttribute("inquiryList", inquiryList);

        System.out.println("inquiryList = " + inquiryList);

        return "/inquiry/list";
    }

}
