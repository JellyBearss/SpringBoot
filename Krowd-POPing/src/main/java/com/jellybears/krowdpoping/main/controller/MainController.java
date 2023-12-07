package com.jellybears.krowdpoping.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("krowdpoping")
public class MainController {

    @GetMapping("mainpage")
    public String goMain(){


        return "main/main";
    }
}
