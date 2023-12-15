package com.jellybears.krowdpoping.project.controller;

import com.jellybears.krowdpoping.common.exception.inquiry.InquirySaveException;
import com.jellybears.krowdpoping.inquiry.model.dto.InquiryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("projectdetail")
public class ProjectController {
    @GetMapping("projectdetail")
    public String goProjectDetail(){

        return "project/projectpage3";
    }


    @GetMapping("projectReportForm")
    public String goProjectReportForm(){

        return "project/projectReportForm";

    }


    @PostMapping("save")
    public String ProjectReportSave() {



        return "redirect:/project/projectpage3";

    }

}
