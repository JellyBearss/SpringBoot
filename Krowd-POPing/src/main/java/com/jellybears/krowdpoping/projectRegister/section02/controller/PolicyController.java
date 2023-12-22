package com.jellybears.krowdpoping.projectRegister.section02.controller;

import com.jellybears.krowdpoping.projectRegister.section02.model.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 정책 페이지 이동
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/projectReg/*")
public class PolicyController {

    private final PolicyService policyService;

    @GetMapping("policy")
    public String goPolicy() {

        return "policy/policy";
    }

}
