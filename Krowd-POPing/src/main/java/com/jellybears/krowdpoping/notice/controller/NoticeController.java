package com.jellybears.krowdpoping.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("notice/*")
public class NoticeController {

    @GetMapping("notice")
    public String defaultAddress() {
        return "/notice/notice";
    }

    @GetMapping("noticeContent")
    public String detailedProduct(){
        return "/notice/notice_content";
    }

}
