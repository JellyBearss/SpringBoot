package com.jellybears.krowdpoping.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

    @GetMapping("memberList")
    public String goMemberList() {
        return "/admin/admin_memberList";
    }

    @GetMapping("fundingList")
    public String goFundingList() {
        return "/admin/admin_fundingList";
    }

    @GetMapping("sponsorshipList")
    public String goSponsorshipList() {
        return "/admin/admin_sponsorshipList";
    }

    @GetMapping("applicationList")
    public String goApplicationList() {
        return "/admin/admin_applicationList";
    }

    @GetMapping("categoryList")
    public String goCategoryList() {
        return "/admin/admin_categoryList";
    }

    @GetMapping("noticeList")
    public String goNoticeList() {
        return "/admin/admin_noticeList";
    }

    @GetMapping("faqList")
    public String goFaqList() {
        return "/admin/admin_faqList";
    }

    @GetMapping("reportList")
    public String goReportList() {
        return "/admin/admin_reportList";
    }

    @GetMapping("inquiryList")
    public String goinquiryList() {
        return "/admin/admin_inquiryList";
    }
    /* End 리스트 ---------------------------------------------------------- */
    @GetMapping("memberDetail")
    public String gomemberDetail() {
        return "/admin/admin_memberDetail";
    }

    @GetMapping("noticeWrite")
    public String gonoticeWrite() {
        return "/admin/admin_noticeWrite";
    }
}
