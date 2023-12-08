package com.jellybears.krowdpoping.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("report")
public class ReportController {

    @GetMapping("report")
    public String goReport(){

        return "report/reportForm";
    }

    @GetMapping("MYP_report")
    public String goMYPReport(){

        return "mypage/MYP_report";
    }

    @GetMapping("MYP_reportContent")
    public String goMYPReportContent(){

        return "mypage/MYP_reportContent";
    }
}
