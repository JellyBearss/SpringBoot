package com.jellybears.krowdpoping.admin.controller;

import com.jellybears.krowdpoping.admin.model.dao.NoticeMapper;
import com.jellybears.krowdpoping.admin.model.dto.NoticeDTO;
import com.jellybears.krowdpoping.admin.model.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/*")
@Slf4j
public class AdminController {

    private final NoticeService noticeService;  // 필드 선언

    @Autowired
    public AdminController(NoticeService noticeService) {   // 생성자 의존성 주입
        this.noticeService = noticeService; // 주입받은 noticeService 객체를 필드에 할당
    }


    /* List ---------------------------------------------------------- */
    /* 유저 전체 목록 페이지 */
    @GetMapping("userList")
    public String goUserList() {

        // List<UserDTO> userList = UserService.selectAllUserList();

        // model.addAttribute("userList", userList);
        return "/admin/admin_userList";
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

/* 공지사항 전체 목록 페이지 */
    @GetMapping("noticeList")
    public String goNoticeList(Model model) {   //  Model 객체를 매개변수로 받고 컨트롤러에서 뷰로 데이터를 전달하는 데 사용

        List<NoticeDTO> noticeList = noticeService.selectAllNoticeList();   // noticeService로 selectAllNoticeList 메서드를 호출하여 공지사항 목록 조회

        model.addAttribute("noticeList", noticeList);   // 조회한 공지사항 목록을 model에 noticeList라는 이름으로 추가
        return "/admin/admin_noticeList";   // 결과 전달
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
    @GetMapping("noticeModify")
    public String goNoticeModify() { return "/admin/admin_noticeModify"; }
    @GetMapping("faqModify")
    public String goFaqModify() { return "/admin/admin_faqModify"; }
    @GetMapping("fundingModify")
    public String goFundingModify() { return "/admin/admin_fundingModify"; }
}
