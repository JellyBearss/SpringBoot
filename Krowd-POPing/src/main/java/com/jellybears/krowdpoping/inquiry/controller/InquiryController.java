package com.jellybears.krowdpoping.inquiry.controller;

import com.jellybears.krowdpoping.common.exception.inquiry.InquirySaveException;
import com.jellybears.krowdpoping.inquiry.model.dto.InquiryDTO;
import com.jellybears.krowdpoping.inquiry.model.service.InquiryService;
import com.jellybears.krowdpoping.inquiry.model.service.InquiryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.attribute.standard.Media;
import java.util.List;

@Controller
@RequestMapping("inquiry/*")
@Slf4j
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }


    /**
     * inquiryContent?id=1  ?이하는 파라미터이다. 파라미터 id, 파라미터 id의 값은 1
     * 파라미터이므로 RequestParam으로 id를 넘겨받고 정수값 1을 넘겨받아야한다. 그래서 int로
     */
//    @GetMapping("inquiryContent")
//    public String goInguiaryContent(@RequestParam("id") int id){
//
//        System.out.println("id = " + id);
//
//        return "/mypage/MYP_inquiryContent";
//    }

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

    @GetMapping("list")
    public String findInquiryList(Model model){

        List<InquiryDTO> inquiryList = inquiryService.findInquiryList();
        List<InquiryDTO> inquiryList2 = inquiryService.findInquiryList2();

        log.info("list {}", inquiryList);

        model.addAttribute("inquiryList", inquiryList);
        model.addAttribute("inquiryList2", inquiryList2);

        System.out.println("inquiryList = " + inquiryList);

        return "/mypage/MYP_inquiry";
    }


}
