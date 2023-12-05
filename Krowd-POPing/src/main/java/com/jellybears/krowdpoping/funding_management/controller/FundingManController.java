package com.jellybears.krowdpoping.funding_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("funding_management/*")
public class FundingManController {

    @GetMapping("management")
    public String goFAQ() {
        return "/funding_management/funding_management";
    }

}
