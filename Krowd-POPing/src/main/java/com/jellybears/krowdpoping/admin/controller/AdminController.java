package com.jellybears.krowdpoping.admin.controller;

import com.jellybears.krowdpoping.admin.model.dto.NoticeDTO;
import com.jellybears.krowdpoping.admin.model.service.NoticeService;
import com.jellybears.krowdpoping.common.exception.admin.notice.NoticeModifyException;
import com.jellybears.krowdpoping.common.exception.admin.notice.NoticeRegistException;
import com.jellybears.krowdpoping.common.exception.admin.notice.NoticeRemoveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/*")
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
            @GetMapping("userDetail")
            public String goUserDetail() {
                return "/admin/admin_userDetail";
            }
            @GetMapping("fundingDetail")
            public String goFundingDetail() { return "/admin/admin_fundingDetail";}
            @GetMapping("applicationDetail")
            public String goApplicationDetail() { return "/admin/admin_applicationDetail";}

            /* 공지사항 상세 페이지 */
            @GetMapping("noticeDetail")
            public String goNoticeDetail(@RequestParam int noticeCode, Model model) {

                NoticeDTO noticeDetail = noticeService.selectNoticeDetail(noticeCode);
                model.addAttribute("notice", noticeDetail);
                return "/admin/admin_noticeDetail";
            }

            @GetMapping("reportDetail")
            public String goReportDetail() { return "/admin/admin_reportDetail";}
            @GetMapping("inquiryDetail")
            public String goInquiryDetail() { return "/admin/admin_inquiryDetail";}
                /* Regist ---------------------------------------------------------- */

                /* 공지사항 내용 추가 페이지 */
                @GetMapping("noticeRegist")
                public String goNoticeRegist() { return "/admin/admin_noticeRegist"; }
                @PostMapping("noticeRegist")
                public String noticeRegist(@ModelAttribute NoticeDTO notice, RedirectAttributes rttr) throws NoticeRegistException {
                    /* 유저 - 나중에 로그인 객체에서 꺼내와야함*/
                    notice.setNoticeAdminUserCode(2);

                    noticeService.noticeRegist(notice);
                    rttr.addFlashAttribute("message", "공지사항 등록을 성공하였습니다.");

                    return "redirect:/admin/noticeList";
                }

                @GetMapping("faqRegist")
                public String goFaqWrite() { return "/admin/admin_faqRegist"; }
                /* Modify ---------------------------------------------------------- */

                /* 공지사항 수정 페이지 */
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

                @GetMapping("faqModify")
                public String goFaqModify() { return "/admin/admin_faqModify"; }
                @GetMapping("fundingModify")
                public String goFundingModify() { return "/admin/admin_fundingModify"; }

                /* Remove ---------------------------------------------------------- */
                @GetMapping("noticeRemove")
                public String noticeRemove(@RequestParam int noticeCode, RedirectAttributes rttr) throws NoticeRemoveException {

                    noticeService.noticeRemove(noticeCode);

                    rttr.addFlashAttribute("message", "공지사항 삭제를 성공하였습니다.");
                    return "redirect:/admin/noticeList";
                }
            }