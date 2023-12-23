package com.jellybears.krowdpoping.kakaopay.controller;

import com.jellybears.krowdpoping.common.exception.Kakaopay.BusinessLogicException;
import com.jellybears.krowdpoping.common.exception.Kakaopay.ExceptionCode;
import com.jellybears.krowdpoping.kakaopay.model.dto.KakaoApproveResponse;
import com.jellybears.krowdpoping.kakaopay.model.dto.KakaoCancelResponse;
import com.jellybears.krowdpoping.kakaopay.model.dto.KakaoReadyResponse;
import com.jellybears.krowdpoping.kakaopay.model.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class KakaoPayController {
    private final KakaoPayService kakaoPayService;


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
