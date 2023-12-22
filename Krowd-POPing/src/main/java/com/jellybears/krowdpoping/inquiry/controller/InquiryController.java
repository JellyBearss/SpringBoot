package com.jellybears.krowdpoping.inquiry.controller;

import com.jellybears.krowdpoping.common.exception.inquiry.InquirySaveException;
import com.jellybears.krowdpoping.inquiry.model.dto.InquiryDTO;
import com.jellybears.krowdpoping.inquiry.model.service.InquiryService;
import com.jellybears.krowdpoping.user.model.dto.RoleTypeDTO;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import com.jellybears.krowdpoping.user.model.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("inquiry/*")
@Slf4j
public class InquiryController {

    private final InquiryService inquiryService;
    private final AuthenticationService authenticationService;

    public InquiryController(InquiryService inquiryService, AuthenticationService authenticationService) {
        this.inquiryService = inquiryService;
        this.authenticationService = authenticationService;
    }



    @GetMapping("inquiryContent")
    public String goInguiryContent(@RequestParam Long no, Model model){

        System.out.println("no = " + no);



        InquiryDTO inquiry = inquiryService.selectInquiryContent(no);
        model.addAttribute("inquiryContent", inquiry);

        System.out.println("inquiry = " + inquiry);

        return "/mypage/MYP_inquiryContent";
    }

    @GetMapping("inquiryForm")
    public void goInguiryForm(){

//        return "/inquiry/inquiryForm";
    }


    @PostMapping("save")
    public String save(@ModelAttribute InquiryDTO inquiry, RedirectAttributes rttr) throws InquirySaveException { //()여기에 DTO에 담은 것을 넘겨준다.
        System.out.println("inquiry = " + inquiry);

        inquiryService.saveNewInquiry(inquiry);

        rttr.addFlashAttribute("message", "문의가 전송되었습니다.");


    return "redirect:/inquiry/list";

    }

//    @GetMapping("list")
//    public String findInquiryList(Model model){
//
//
//        List<InquiryDTO> inquiryList = inquiryService.findInquiryList();
//        List<InquiryDTO> inquiryList2 = inquiryService.findInquiryList2();
//
//        log.info("list {}", inquiryList);
//
//        model.addAttribute("inquiryList", inquiryList);
//        model.addAttribute("inquiryList2", inquiryList2);
//
//        System.out.println("inquiryList = " + inquiryList);
//
//        return "/mypage/MYP_inquiry";
//    }

    /**
     * 로그인한 유저 정보에 따른 문의 내역이 보이게 설정.
     * @param model
     * @return
     */
    @GetMapping("list")
    public String findInquiryList(Model model){

        //현재 로그인한 사용자의 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //getPrincipal(); 은 authentication 객체에서 주체를 반환함. 즉 현재 로그인한 사용자의 세부 정보.
        //UserDetails 인터페이스에서 가져오는 것.
        UserDTO loggedInUser = ((RoleTypeDTO) authentication.getPrincipal()).getUserDTO();
        int userCode = loggedInUser.getUser_code();

        //로그인한 사용자의 user_code를 기반으로 문의 목록 조회
        List<InquiryDTO> inquiryList = inquiryService.findInquiryListByUSerCode(userCode);
        List<InquiryDTO> inquiryList2 = inquiryService.findInquiryList2();

        log.info("list {}", inquiryList);

        model.addAttribute("inquiryList", inquiryList);
        model.addAttribute("inquiryList2", inquiryList2);

        System.out.println("inquiryList = " + inquiryList);

        return "/mypage/MYP_inquiry";
    }


}
