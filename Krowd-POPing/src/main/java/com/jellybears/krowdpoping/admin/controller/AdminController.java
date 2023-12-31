package com.jellybears.krowdpoping.admin.controller;

import com.jellybears.krowdpoping.admin.model.dto.AdminFundingDTO;
import com.jellybears.krowdpoping.admin.model.dto.AdminSponsorshipDTO;
import com.jellybears.krowdpoping.admin.model.dto.NoticeDTO;
import com.jellybears.krowdpoping.admin.model.service.*;
import com.jellybears.krowdpoping.common.exception.admin.notice.*;
import com.jellybears.krowdpoping.inquiry.model.dto.InquiryDTO;
import com.jellybears.krowdpoping.report.model.dto.ProjectReportDTO;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

    private final NoticeService noticeService;  // 필드 선언
    private final AdminUserService adminUserService;
    private final AdminInquiryService adminInquiryService;
    private final AdminFundingService adminFundingService;
    private final AdminReportService adminReportService;
    private final AdminSponsorshipService adminSponsorshipService;
    private final AdminProjectService adminProjectService;

    @Autowired
    public AdminController(NoticeService noticeService, AdminUserService adminUserService, AdminInquiryService adminInquiryService, AdminFundingService adminFundingService, AdminReportService adminReportService, AdminSponsorshipService adminSponsorshipService, AdminProjectService adminProjectService) {   // 생성자 의존성 주입

        this.noticeService = noticeService; // 주입받은 noticeService 객체를 필드에 할당
        this.adminUserService = adminUserService;
        this.adminInquiryService = adminInquiryService;
        this.adminFundingService = adminFundingService;
        this.adminReportService = adminReportService;
        this.adminSponsorshipService = adminSponsorshipService;
        this.adminProjectService = adminProjectService;
    }
    /* ----------------------------------------------------------------------------------------------------------------- */
    /* 회원 목록 */
    @GetMapping("userList")
    public String goUserList(Model model) {

        List<UserDTO> userList = adminUserService.selectAllUserList();
        model.addAttribute("userList", userList);
        return "/admin/admin_userList";
    }
    /* 회원 상세 */
    @GetMapping("userDetail")
    public String goUserDetail(@RequestParam int user_code, Model model) {

        UserDTO userDetail = adminUserService.selectUserDetail(user_code);
        model.addAttribute("user", userDetail);
        return "/admin/admin_userDetail";

    }
    /* ----------------------------------------------------------------------------------------------------------------- */
    /* 펀딩 목록 */
    @GetMapping("fundingList")
    public String goFundingList(Model model) {

        List<AdminFundingDTO> fundingList = adminFundingService.selectAllFundingList();
        model.addAttribute("fundingList", fundingList);
        return "/admin/admin_fundingList";
    }
    /* 펀딩 상세 */
    @GetMapping("fundingDetail")
    public String goFundingDetail(@RequestParam int projectCode, Model model) {

        AdminFundingDTO fundingDetail = adminFundingService.selectFundingDetail(projectCode);
        model.addAttribute("funding", fundingDetail);
        return "/admin/admin_fundingDetail";}
    /* 펀딩 수정 */
    @GetMapping("fundingModify")
    public String goFundingModify(@RequestParam int projectCode, Model model) {

        AdminFundingDTO funding = adminFundingService.selectFundingDetail(projectCode);
        model.addAttribute("funding", funding);
        return "/admin/admin_fundingModify";
    }
    @PostMapping("fundingModify")
    public String fundingModify(@ModelAttribute AdminFundingDTO funding, RedirectAttributes rttr) throws FundingModifyException {

        adminFundingService.fundingModify(funding);
        rttr.addFlashAttribute("message", "펀딩 수정을 성공하였습니다.");
        return "redirect:/admin/fundingList";
    }
    /* ----------------------------------------------------------------------------------------------------------------- */
    /* 후원 목록 */
    @GetMapping("sponsorshipList")
    public String goSponsorshipList(Model model) {

        List<AdminSponsorshipDTO> sponsorshipList = adminSponsorshipService.selectAllSponsorshipList();
        model.addAttribute("sponsorshipList", sponsorshipList);
        return "/admin/admin_sponsorshipList";
    }
    /* ----------------------------------------------------------------------------------------------------------------- */
    /* 프로젝트 목록 */
    @GetMapping("projectList")
    public String goProjectList(Model model) {

        List<AdminFundingDTO> projectList = adminProjectService.selectAllProjectList();
        model.addAttribute("projectList", projectList);
        return "/admin/admin_projectList";
    }
    /* 프로젝트 상세 */
    @GetMapping("projectDetail")
    public String goProjectDetail(@RequestParam("projectCode") int projectCode, Model model) {

        System.out.println("=========================================="+projectCode);
        AdminFundingDTO projectDetail = adminProjectService.selectProjectDetail(projectCode);
        System.out.println("projectDetail ================================== " + projectDetail);
        model.addAttribute("project", projectDetail);
        return "/admin/admin_projectDetail";
    }

    /* 프로젝트 승인 */

    @PostMapping("/fundingAccept")
    public String fundingAccept(@ModelAttribute AdminFundingDTO adminFundingDTO, RedirectAttributes rttr) throws FundingAcceptException {

        System.out.println("========================================== fundingAccept :"+adminFundingDTO);

        adminProjectService.fundingAccept(adminFundingDTO);

        rttr.addFlashAttribute("message", "승인 성공하였습니다.");
        return "redirect:/admin/projectList";

    }

    /* 프로젝트 반려 */
    @PostMapping("/fundingReject")
    public String fundingReject(@ModelAttribute AdminFundingDTO adminFundingDTO, RedirectAttributes rttr) throws FundingRejectException {

        System.out.println("==================반려"+adminFundingDTO);

        adminProjectService.fundingReject(adminFundingDTO);

        rttr.addFlashAttribute("message", "반려 성공하였습니다.");
        return "redirect:/admin/projectList";

    }
    /* ----------------------------------------------------------------------------------------------------------------- */
    /* 카테고리 목록 */
    @GetMapping("categoryList")
    public String goCategoryList() {
        return "/admin/admin_categoryList";
    }
    /* ----------------------------------------------------------------------------------------------------------------- */
    /* 공지사항 목록 */
    @GetMapping("noticeList")
    public String goNoticeList(Model model) {   //  Model 객체를 매개변수로 받고 컨트롤러에서 뷰로 데이터를 전달하는 데 사용

        List<NoticeDTO> noticeList = noticeService.selectAllNoticeList();   // noticeService로 selectAllNoticeList 메서드를 호출하여 공지사항 목록 조회
        model.addAttribute("noticeList", noticeList);   // 조회한 공지사항 목록을 model에 noticeList라는 이름으로 추가
        return "/admin/admin_noticeList";   // 결과 전달
    }
    /* 공지사항 상세 */
    @GetMapping("noticeDetail")
    public String goNoticeDetail(@RequestParam int noticeCode, Model model) {

        NoticeDTO noticeDetail = noticeService.selectNoticeDetail(noticeCode);
        model.addAttribute("notice", noticeDetail);
        return "/admin/admin_noticeDetail";
    }
    /* 공지사항 등록 */
    @GetMapping("noticeRegist")
    public String goNoticeRegist() { return "/admin/admin_noticeRegist"; }
    @PostMapping("noticeRegist")
    public String noticeRegist(@ModelAttribute NoticeDTO notice, RedirectAttributes rttr) throws NoticeRegistException {

        notice.setNoticeAdminUserCode(2); /* 유저 - 나중에 로그인 객체에서 꺼내와야함*/
        noticeService.noticeRegist(notice);
        rttr.addFlashAttribute("message", "공지사항 등록을 성공하였습니다.");
        return "redirect:/admin/noticeList";
    }
    /* 공지사항 수정 */
    @GetMapping("noticeModify")
    public String goNoticeModify(@RequestParam int noticeCode, Model model) {

        NoticeDTO notice = noticeService.selectNoticeDetail(noticeCode);
        model.addAttribute("notice", notice);
        return "/admin/admin_noticeModify";
    }
    @PostMapping("noticeModify")
    public String noticeModify(@ModelAttribute NoticeDTO notice, RedirectAttributes rttr) throws NoticeModifyException {

        noticeService.noticeModify(notice);
        rttr.addFlashAttribute("message", "공지사항 수정을 성공하였습니다.");
        return "redirect:/admin/noticeList";
    }
    /* 공지사항 삭제 */
    @GetMapping("noticeRemove")
    public String noticeRemove(@RequestParam int noticeCode, RedirectAttributes rttr) throws NoticeRemoveException {

        noticeService.noticeRemove(noticeCode);
        rttr.addFlashAttribute("message", "공지사항 삭제를 성공하였습니다.");
        return "redirect:/admin/noticeList";
    }
    /* ----------------------------------------------------------------------------------------------------------------- */
    /* F.A.Q 목록 */
    @GetMapping("faqList")
    public String goFaqList() {
        return "/admin/admin_faqList";
    }
    /* F.A.Q 등록 */
    @GetMapping("faqRegist")
    public String goFaqWrite() { return "/admin/admin_faqRegist"; }
    /* F.A.Q 수정 */
    @GetMapping("faqModify")
    public String goFaqModify() { return "/admin/admin_faqModify"; }
    /* F.A.Q 삭제 */
    @GetMapping("faqRemove")
    public String faqRemove() {
        return "redirect:/admin/faqList";
    }
    /* ----------------------------------------------------------------------------------------------------------------- */
    /* 신고 목록 */
    @GetMapping("reportList")
    public String goReportList(Model model) {

        List<ProjectReportDTO> reportList = adminReportService.selectAllReportList();
        model.addAttribute("reportList", reportList);
        return "/admin/admin_reportList";
    }
    /* 신고 상세 */
    @GetMapping("reportDetail")
    public String goReportDetail() { return "/admin/admin_reportDetail";}
    /* ----------------------------------------------------------------------------------------------------------------- */
    /* 문의 목록 */
    @GetMapping("inquiryList")
    public String goInquiryList(Model model) {

        List<InquiryDTO> inquiryList = adminInquiryService.selectAllInquiryList();
        model.addAttribute("inquiryList", inquiryList);
        return "/admin/admin_inquiryList";
    }
    /* 문의 상세 */
    @GetMapping("inquiryDetail")
    public String goInquiryDetail() { return "/admin/admin_inquiryDetail";}
}