package com.jellybears.krowdpoping.calculate.controller;

import com.jellybears.krowdpoping.calculate.model.dto.CalculateDTO;
import com.jellybears.krowdpoping.calculate.model.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/projectList")
@Controller
public class CalculateController {

    private final CalculateService calculateService;

    @Autowired
    public CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

   @GetMapping("/calculate")
    public String goProjectDetail(@RequestParam(name="no") Long no, Model model) {

        System.out.println("no = " + no);


        CalculateDTO calculate = calculateService.selectCalculate(no);
        model.addAttribute("calculate", calculate);
        System.out.println("calculate = " + calculate);

        return "calculateInfo/CalculateInfo2";
    }
}

