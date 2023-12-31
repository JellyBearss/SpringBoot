package com.jellybears.krowdpoping.mypage.controller;

import com.jellybears.krowdpoping.common.exception.user.UserModifyException;
import com.jellybears.krowdpoping.common.util.SessionUtil;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import com.jellybears.krowdpoping.user.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mypage/*")
@Slf4j
public class MyPageController {

    /**
     * 마이페이지-후원한 프로젝트(진행중인)
     */
    @GetMapping("projectlist1_ing")
    public String projectList1_ing() {

        return "mypage/mypage_projectlist_1_ing.html";
    }
    /**
     * 마이페이지-후원한 프로젝트(종료된)
     */
    @GetMapping("projectlist1_end")
    public String projectList1_end() {

        return "mypage/mypage_projectlist_1_end.html";
    }

    /**
     * 마이페이지-찜한 프로젝트(있을때)
     */
    @GetMapping("projectlist2_yes")
    public String projectList2_yes() {

        return "mypage/mypage_projectlist_2_yes.html";
    }

    /**
     * 마이페이지-찜한 프로젝트(없을때)
     */
    @GetMapping("projectlist2_no")
    public String projectList2_no() {

        return "mypage/mypage_projectlist_2_no.html";
    }

    /**
     * 마이페이지-등록한 프로젝트()
     */
    @GetMapping("projectlist3_no")
    public String projectList3_no() {

        return "mypage/mypage_projectlist_3_no.html";
    }

    /**
     * 마이페이지- 최근 본 프로젝트
     */

    @GetMapping("projectlist4")
    public String projectList4() {

        return "mypage/mypage_projectlist_4.html";
    }


    /**
     * 마이페이지- 팔로워 리스트 (있을때)
     */
    @GetMapping("followerlist_yes")
    public String followerList_yes() {

        return "mypage/mypage_followerlist_yes.html";
    }
    /**
     * 마이페이지- 팔로워 리스트 (없을때)
     */
    @GetMapping("followerlist_no")
    public String followerList_no() {

        return "mypage/mypage_followerlist_no.html";
    }

    /**
     * 마이페이지- 팔로잉 리스트 (있을때)
     */
    @GetMapping("followinglist_yes")
    public String followingList_yes() {

        return "mypage/mypage_followinglist_yes.html";
    }
    /**
     * 마이페이지- 팔로잉 리스트 (없을때)
     */
    @GetMapping("followinglist_no")
    public String followingList_no() {

        return "mypage/mypage_followinglist_no.html";
    }


}
