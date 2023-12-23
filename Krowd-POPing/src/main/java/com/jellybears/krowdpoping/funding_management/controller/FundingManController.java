package com.jellybears.krowdpoping.funding_management.controller;

import com.jellybears.krowdpoping.project.model.dto.DetailGoodsDTO;
import com.jellybears.krowdpoping.project.model.dto.DetailProjectDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("funding_management/*")
public class FundingManController {

    @GetMapping("management")
    public String management(HttpSession session,
                             Model model) {
        // 세션에서 goods 정보 가져오기
        DetailProjectDTO detail = (DetailProjectDTO) session.getAttribute("detail");
        DetailGoodsDTO goods = (DetailGoodsDTO) session.getAttribute("goods");

        // goods 정보를 모델에 추가
        model.addAttribute("detail", detail);
        model.addAttribute("goods", goods);

        return "/funding_management/funding_management";
    }

}
