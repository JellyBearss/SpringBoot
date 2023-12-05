package com.jellybears.krowdpoping.inquiary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("inquiary")
public class InquiaryController {

    @GetMapping("inquiary")
    public String goInguiary(){

        return "inquiary/report";
    }
}
