package com.jellybears.krowdpoping.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

    /* List ---------------------------------------------------------- */
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
    public String goInquiryList() {
        return "/admin/admin_inquiryList";
    }
    /* Detail ---------------------------------------------------------- */
    @GetMapping("memberDetail")
    public String gomemberDetail() {
        return "/admin/admin_memberDetail";
    }
    @GetMapping("fundingDetail")
    public String goFundingDetail() { return "/admin/admin_fundingDetail";}
    @GetMapping("applicationDetail")
    public String goApplicationDetail() { return "/admin/admin_applicationDetail";}
    @GetMapping("noticeDetail")
    public String goNoticeDetail() { return "/admin/admin_noticeDetail";}
    @GetMapping("reportDetail")
    public String goReportDetail() { return "/admin/admin_reportDetail";}
    @GetMapping("inquiryDetail")
    public String goInquiryDetail() { return "/admin/admin_inquiryDetail";}
    /* Write ---------------------------------------------------------- */
    @GetMapping("noticeWrite")
    public String goNoticeWrite() {
        return "/admin/admin_noticeWrite";
    }
    @GetMapping("faqWrite")
    public String goFaqWrite() { return "/admin/admin_faqWrite"; }
    /* Modify ---------------------------------------------------------- */
    @GetMapping("faqModify")
    public String goFaqModify() { return "/admin/admin_faqModify"; }
    @GetMapping("noticeModify")
    public String goNoticeModify() { return "/admin/admin_noticeModify"; }
    @GetMapping("fundingModify")
    public String goFundingModify() { return "/admin/admin_fundingModify"; }
}
