package com.jellybears.krowdpoping.projectRegister.section02.controller;

import com.jellybears.krowdpoping.projectList.model.dto.ProjectDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.dto.PolicyDTO;
import com.jellybears.krowdpoping.projectRegister.section02.model.service.PolicyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 정책 페이지 이동
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/projectReg")
@Slf4j
public class PolicyController {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${spring.servlet.multipart.location}")
    private String ROOT_LOCATION;

    private final PolicyService policyService;

    @GetMapping("/policy")
    public String goPolicy() {
        return "policy/policy";
    }

    @PostMapping("/policyReg")
    public String registerPolicy(Model model, PolicyDTO policy) {
        ProjectDTO project=new ProjectDTO();
        policy.setProjectCode(project.getProjectCode());

        return "/projectReg/policy";
    }

    /** 정책데이터 및 첨부파일 저장 */
    @ResponseBody
    @PostMapping("/save")
    public String savePolicy(@RequestPart(value = "data") PolicyDTO dto, @RequestPart(value = "files",required = false) MultipartFile[] files) throws IOException {
        policyService.procPolicy(dto, files); // 정책데이터 및 첨부파일 저장

        return "redirect:/policy/policy";
    }

}
