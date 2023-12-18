package com.jellybears.krowdpoping.report.controller;

import com.jellybears.krowdpoping.report.model.dto.ProjectReportDTO;
import com.jellybears.krowdpoping.report.model.service.ProjectReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("report")
public class ReportController {

    private final ProjectReportService projectReportService;

    public ReportController(ProjectReportService projectReportService) {
        this.projectReportService = projectReportService;
    }

//    @GetMapping("ProjectReport")
//    public String goReport(){
//
//        return "report/ProjectReportForm";
//    }

    @GetMapping("list")
    public String findReportList(Model model){

        List<ProjectReportDTO> projectReportList = projectReportService.findReportList();

        System.out.println("projectReportList = " + projectReportList);

        model.addAttribute("projectReportList", projectReportList);


        return "mypage/MYP_report";
    }

    @GetMapping("MYP_reportContent")
    public String goMYPReportContent(){

        return "mypage/MYP_reportContent";
    }


    /**
     * 프로젝트 신고
     * @return
     */
//    @GetMapping("projectReportForm")
//    public String goProjectReportForm(@RequestParam("projectId") int projectId, Model model){
//
//        model.addAttribute("reportProjectCode", projectId);
//        System.out.println("projectId = " + projectId);
//
//        return "project/projectReportForm";
//    }

    @GetMapping("ProjectReport")
    public String goProjectReportForm(@RequestParam("no") int projectCode, Model model) {
        System.out.println("projectCode ===============================> " + projectCode);
        model.addAttribute("reportProjectCode", projectCode);
        return "report/projectReportForm";
    }


    /**
     * 프로젝트 리포트 작성
     * @param projectReportDTO 작성폼의 내용이 담긴 DTO
     * @return
     */
    @PostMapping("save")
    public String ProjectReportSave(@ModelAttribute ProjectReportDTO projectReportDTO) throws IOException {
        System.out.println("projectReportDTO = " + projectReportDTO);



        projectReportService.saveReport(projectReportDTO);

        return "redirect:/report/list";

    }

}
