package com.jellybears.krowdpoping.FAQ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("FAQ/*")
public class FAQController {

    @GetMapping("FAQ")
    public String goFAQ() {
        return "/FAQ/FAQ";
    }

}
