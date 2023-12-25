package com.jellybears.krowdpoping.funding_management.controller;

import com.jellybears.krowdpoping.funding_process.model.dto.AddressDTO;
import com.jellybears.krowdpoping.funding_process.model.dto.ProductDTO;
import com.jellybears.krowdpoping.funding_process.model.service.FundingServiceImpl;
import com.jellybears.krowdpoping.kakaopay.model.dto.KakaoCancelResponse;
import com.jellybears.krowdpoping.kakaopay.model.service.KakaoPayService;
import com.jellybears.krowdpoping.project.model.dto.DetailGoodsDTO;
import com.jellybears.krowdpoping.project.model.dto.DetailProjectDTO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("funding_management/*")
@Slf4j
public class FundingManController {
    private final FundingServiceImpl fundingService;
    private final KakaoPayService kakaoPayService;

    @Autowired
    public FundingManController(FundingServiceImpl fundingService,
                                KakaoPayService kakaoPayService){
        this.fundingService = fundingService;
        this.kakaoPayService = kakaoPayService;
    }

    @GetMapping("management")
    public String management(HttpSession session,
                             Model model) {


        // 세션에서 goods 정보 가져오기
        DetailProjectDTO detail = (DetailProjectDTO) session.getAttribute("detail");
        DetailGoodsDTO goods = (DetailGoodsDTO) session.getAttribute("goods");

        // 세션에서 저장된 productDTO 정보 가져오기
        int user_code = (int) session.getAttribute("user_code");
        AddressDTO addressDTO = (AddressDTO) session.getAttribute("addressDTO");
        ProductDTO savedProductDTO = (ProductDTO) session.getAttribute("savedProductDTO");

        // goods 정보를 모델에 추가
        model.addAttribute("detail", detail);
        model.addAttribute("goods", goods);
        model.addAttribute("user_code", user_code);
        model.addAttribute("addressDTO", addressDTO);

        // 세션에서 가져온 정보를 모델에 추가
        model.addAttribute("savedProductDTO", savedProductDTO);
        double achievement = (double) detail.getSumPayAmount() / detail.getTargetAmount() * 100;
        double roundedAchievement = Math.round(achievement * 10.0) / 10.0;
        model.addAttribute("roundedAchievement", roundedAchievement);

        return "/funding_management/funding_management";
    }

    /**
     * 환불
     */
    @PostMapping("/refund")
    public ResponseEntity refund(HttpSession session) {

        // 이미 취소된 경우 처리
        if ("후원 취소".equals(session.getAttribute("refundStatus"))) {
            return new ResponseEntity<>("이미 취소 처리된 후원 건입니다.", HttpStatus.BAD_REQUEST);
        }

        KakaoCancelResponse kakaoCancelResponse = kakaoPayService.kakaoCancel();

        // 세션에서 저장된 productDTO 정보 가져오기
        ProductDTO savedProductDTO = (ProductDTO) session.getAttribute("savedProductDTO");

        // 저장된 ProductDTO의 정보를 사용하여 결제 정보 업데이트
        fundingService.saveCancel(savedProductDTO);

        // 이미 취소된 상태를 세션에 저장
        session.setAttribute("refundStatus", "후원 취소");

        return new ResponseEntity<>(kakaoCancelResponse, HttpStatus.OK);
    }
}
