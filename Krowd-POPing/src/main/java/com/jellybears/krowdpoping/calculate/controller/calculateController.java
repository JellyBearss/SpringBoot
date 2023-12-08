package com.jellybears.krowdpoping.calculate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/projectList/*")
@Controller
public class calculateController {

    @GetMapping("calculate")
    public String goCalculate() {
        return "calculateInfo/CalculateInfo2";
    }
}
