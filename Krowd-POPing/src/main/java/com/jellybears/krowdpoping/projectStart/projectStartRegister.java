package com.jellybears.krowdpoping.projectStart;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/start-Project/*")
public class projectStartRegister {

    @GetMapping("start")
    public String startRegister() {

        return "projectStart/projectStart";
    }

    @GetMapping("check")
    public String startCheckRegister() {

        return "projectStart/projectStartCheck";
    }

}
