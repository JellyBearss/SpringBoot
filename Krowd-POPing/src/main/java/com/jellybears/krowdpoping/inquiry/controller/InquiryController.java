package com.jellybears.krowdpoping.inquiry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("inquiry")
public class InquiryController {

    @GetMapping("inquiry")
    public String goInguiary(){

        return "/mypage/MYP_inquiry";
    }

    @GetMapping("inquiryContent")
    public String goInguiaryContent(){

        return "/mypage/MYP_inquiryContent";
    }
}
