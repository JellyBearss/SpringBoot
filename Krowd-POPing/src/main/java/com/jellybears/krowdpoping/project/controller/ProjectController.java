package com.jellybears.krowdpoping.project.controller;

import com.jellybears.krowdpoping.project.model.dto.*;
import com.jellybears.krowdpoping.project.model.service.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("projectdetail")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * 프로젝트 상세페이지
     * @return
     */
    @GetMapping("projectdetail")
    public String goProjectDetail(@RequestParam Long no,
                                  Model model){

        System.out.println("no = " + no);

        DetailProjectDTO detail = projectService.goProjectDetail(no);
        model.addAttribute("detail", detail);
        System.out.println("detail = " + detail);

        DetailProjectImageDTO projectFile = projectService.getProjectFile(no);
        model.addAttribute("projectFile", projectFile);

        //남은 기간 계산
        LocalDate startDate = detail.getStartDate().toLocalDate();
        LocalDate endDate = detail.getEndDate().toLocalDate();
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), endDate);

        // 남은 기간이 마이너스일 경우 '종료됨'으로 설정
        String daysLeftDisplay = daysLeft > 0 ? daysLeft + "일 남음" : "종료됨";

        // 타임리프로 전달해줄 내용
        model.addAttribute("daysLeftDisplay", daysLeftDisplay);


        return "project/projectpage3";
    }




}
