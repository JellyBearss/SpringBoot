package com.jellybears.krowdpoping.funding_process.controller;

import com.jellybears.krowdpoping.common.exception.Kakaopay.BusinessLogicException;
import com.jellybears.krowdpoping.common.exception.Kakaopay.ExceptionCode;
import com.jellybears.krowdpoping.common.exception.address.AddressSaveException;
import com.jellybears.krowdpoping.funding_process.model.dto.AddressDTO;
import com.jellybears.krowdpoping.funding_process.model.dto.ProductDTO;
import com.jellybears.krowdpoping.funding_process.model.service.FundingServiceImpl;
import com.jellybears.krowdpoping.kakaopay.model.dto.KakaoApproveResponse;
import com.jellybears.krowdpoping.kakaopay.model.dto.KakaoCancelResponse;
import com.jellybears.krowdpoping.kakaopay.model.dto.KakaoReadyResponse;
import com.jellybears.krowdpoping.kakaopay.model.service.KakaoPayService;
import com.jellybears.krowdpoping.project.model.dto.DetailGoodsDTO;
import com.jellybears.krowdpoping.project.model.dto.DetailProjectDTO;
import com.jellybears.krowdpoping.project.model.service.ProjectService;
import com.jellybears.krowdpoping.user.model.dto.RoleTypeDTO;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import com.jellybears.krowdpoping.user.model.service.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/funding_process")
@Slf4j
public class FundingProcessController {

    private final FundingServiceImpl fundingService;
    private final AuthenticationService authenticationService;
    private final ProjectService projectService;
    private final KakaoPayService kakaoPayService;

    @Autowired
    public FundingProcessController(FundingServiceImpl fundingService,
                                    AuthenticationService authenticationService,
                                    ProjectService projectService,
                                    KakaoPayService kakaoPayService){
        this.fundingService = fundingService;
        this.authenticationService = authenticationService;
        this.projectService = projectService;
        this.kakaoPayService = kakaoPayService;
    }

    @GetMapping("product")
    public String detailedProduct(@RequestParam Long no,
                                  @RequestParam int goodsCode,
                                  Model model,
                                  HttpSession session){
        DetailProjectDTO detail = projectService.goProjectDetail(no);
        model.addAttribute("detail", detail);

        DetailGoodsDTO goods = projectService.getGoodsDetails(goodsCode);
        model.addAttribute("goods", goods);

        //남은 기간 계산
        LocalDate startDate = detail.getStartDate().toLocalDate();
        LocalDate endDate = detail.getEndDate().toLocalDate();
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), endDate);

        // 남은 기간이 마이너스일 경우 '종료됨'으로 설정
        String daysLeftDisplay = daysLeft > 0 ? daysLeft + "일 남음" : "종료됨";

        // 타임리프로 전달해줄 내용
        model.addAttribute("daysLeftDisplay", daysLeftDisplay);

        // 세션에 goods 정보 저장
        session.setAttribute("detail", detail);
        session.setAttribute("goods", goods);

        return "funding_process/detailed_product";
    }

    @GetMapping("address")
    public String defaultAddress(@RequestParam Long no,
                                 @RequestParam int goodsCode,
                                 Model model) {
        DetailProjectDTO detail = projectService.goProjectDetail(no);
        model.addAttribute("detail", detail);

        DetailGoodsDTO goods = projectService.getGoodsDetails(goodsCode);
        model.addAttribute("goods", goods);

        // 현재 로그인한 사용자의 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // AuthenticationService에서 반환하는 UserDetails를 가져옴
        UserDetails userDetails = authenticationService.loadUserByUsername(authentication.getName());

        if (userDetails instanceof RoleTypeDTO) {
            // RoleTypeDTO인 경우 UserDTO를 추출
            UserDTO loggedInUser = ((RoleTypeDTO) userDetails).getUserDTO();

            // 로그인한 사용자의 기본 주소를 가져와서 모델에 추가
            AddressDTO defaultAddress = fundingService.getDefaultAddress(String.valueOf(loggedInUser.getUser_code()));

            String[] addressParts = defaultAddress.getMergedAddress().split("\\$");
            defaultAddress.setZipCode(addressParts[0]);
            defaultAddress.setRecipientAddress(addressParts[1]);
            defaultAddress.setDetailedAddress(addressParts[2]);

            // 모델에 주소 객체도 추가
            model.addAttribute("defaultAddress", defaultAddress);
            model.addAttribute("user_code", loggedInUser.getUser_code());
        }
        return "funding_process/default_address";
    }
    @PostMapping("saveAddress")
    public String saveAddress(@ModelAttribute AddressDTO addressDTO) throws AddressSaveException {
        log.info("Received AddressDTO: {}", addressDTO);

        String cleanedPhoneNumber = addressDTO.getRecipientPhoneNumber().replace("-", "");
        addressDTO.setRecipientPhoneNumber(cleanedPhoneNumber);

        fundingService.saveAddress(addressDTO);
        return "funding_process/pay_reservation";
    }

    @GetMapping("payReservation")
    public String payReservation(@RequestParam Long no,
                                 @RequestParam int goodsCode,
                                 Model model){

        DetailGoodsDTO goods = projectService.getGoodsDetails(goodsCode);
        model.addAttribute("goods", goods);

        // 현재 로그인한 사용자의 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // AuthenticationService에서 반환하는 UserDetails를 가져옴
        UserDetails userDetails = authenticationService.loadUserByUsername(authentication.getName());

        if (userDetails instanceof RoleTypeDTO) {
            // RoleTypeDTO인 경우 UserDTO를 추출
            UserDTO loggedInUser = ((RoleTypeDTO) userDetails).getUserDTO();

            // ProductDTO 생성 및 설정
            ProductDTO productDTO = new ProductDTO();
            productDTO.setUser_code(loggedInUser.getUser_code());
            productDTO.setGoodsCode(goodsCode);

            // 기존에 이미 가져온 goods 객체를 사용하여 DetailGoodsDTO 설정
            productDTO.setDetailGoodsDTO(goods);

            // totalAmount 계산 (goods.amount + 배송비)
            int deliveryFee = (goods.getAmount() >= 30000) ? 0 : 4000;
            int totalAmount = goods.getAmount() + deliveryFee;

            // 계산된 totalAmount를 ProductDTO에 설정합니다.
            productDTO.setTotalAmount(totalAmount);

            // savePaymentInfo 호출
            fundingService.savePaymentInfo(productDTO);

            // 모델에 사용자 코드 추가
            model.addAttribute("user_code", loggedInUser.getUser_code());
        }

        return "funding_process/pay_reservation";
    }

    @PostMapping("savePaymentInfo")
    public String savePaymentInfo(@ModelAttribute ProductDTO productDTO,
                                  Model model){

        int goodsAmount = productDTO.getDetailGoodsDTO().getAmount();
        // totalAmount 계산 (goods.amount + 배송비)
        int deliveryFee = (goodsAmount >= 30000) ? 0 : 4000;
        int totalAmount = goodsAmount + deliveryFee;

        // 계산된 totalAmount를 ProductDTO에 설정합니다.
        productDTO.setTotalAmount(totalAmount);

        productDTO.generateRandomValues();

        fundingService.savePaymentInfo(productDTO);

        return "funding_process/process_finished";
    }

    @PostMapping("/ready")
    @ResponseBody
    public ResponseEntity<KakaoReadyResponse> readyToKakaoPay() {
        KakaoReadyResponse response = kakaoPayService.kakaoPayReady();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/processFinished")
    public String processFinished(@RequestParam("pg_token") String pgToken,
                                  Model model){
        System.out.println("Received pg_token: " + pgToken);
        KakaoApproveResponse kakaoApprove = kakaoPayService.approveResponse(pgToken);

        // 데이터를 모델에 추가
        model.addAttribute("kakaoApprove", kakaoApprove);

        // 현재 로그인한 사용자 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = authenticationService.loadUserByUsername(authentication.getName());

        if (userDetails instanceof RoleTypeDTO) {
            // RoleTypeDTO인 경우 UserDTO를 추출
            UserDTO loggedInUser = ((RoleTypeDTO) userDetails).getUserDTO();

            // 닉네임을 모델에 추가
            model.addAttribute("userNickname", loggedInUser.getNickname());
        }
        // 리다이렉션할 뷰 페이지 리턴
        return "funding_process/process_finished";
    }

    /**
     * 결제 진행 중 취소
     */
    @GetMapping("/cancel")
    public void cancel() {

        throw new BusinessLogicException(ExceptionCode.PAY_CANCEL);
    }

    /**
     * 결제 실패
     */
    @GetMapping("/fail")
    public void fail() {

        throw new BusinessLogicException(ExceptionCode.PAY_FAILED);
    }

    /**
     * 환불
     */
    @PostMapping("/refund")
    public ResponseEntity refund() {

        KakaoCancelResponse kakaoCancelResponse = kakaoPayService.kakaoCancel();

        return new ResponseEntity<>(kakaoCancelResponse, HttpStatus.OK);
    }
}

