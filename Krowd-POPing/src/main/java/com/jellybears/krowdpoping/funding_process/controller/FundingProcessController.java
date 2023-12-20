package com.jellybears.krowdpoping.funding_process.controller;

import com.jellybears.krowdpoping.common.exception.address.AddressSaveException;
import com.jellybears.krowdpoping.funding_process.model.dto.AddressDTO;
import com.jellybears.krowdpoping.funding_process.model.service.FundingServiceImpl;
import com.jellybears.krowdpoping.project.model.dto.DetailGoodsDTO;
import com.jellybears.krowdpoping.project.model.dto.DetailProjectDTO;
import com.jellybears.krowdpoping.project.model.service.ProjectService;
import com.jellybears.krowdpoping.user.model.dto.RoleTypeDTO;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import com.jellybears.krowdpoping.user.model.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping("/funding_process")
@Slf4j
public class FundingProcessController {

    private final FundingServiceImpl fundingService;
    private final AuthenticationService authenticationService;
    private final ProjectService projectService;

    @Autowired
    public FundingProcessController(FundingServiceImpl fundingService,
                                    AuthenticationService authenticationService,
                                    ProjectService projectService){
        this.fundingService = fundingService;
        this.authenticationService = authenticationService;
        this.projectService = projectService;
    }
    @GetMapping("address")
    public String defaultAddress(Model model) {
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
        }
        return "funding_process/default_address";
    }
    @PostMapping("saveAddress")
    public String saveAddress(@ModelAttribute AddressDTO addressDTO) throws AddressSaveException {
        log.info("Received AddressDTO: {}", addressDTO);

        String cleanedPhoneNumber = addressDTO.getRecipientPhoneNumber().replace("-", "");
        addressDTO.setRecipientPhoneNumber(cleanedPhoneNumber);

        fundingService.saveAddress(addressDTO);
        return "/funding_process/pay_reservation";
    }

    @GetMapping("product")
    public String detailedProduct(@RequestParam Long no,
                                  @RequestParam int goodsCode,
                                  Model model){
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


        return "/funding_process/detailed_product";
    }

    @GetMapping("payReservation")
    public String payReservation(){
        return "/funding_process/pay_reservation";
    }

    @GetMapping("processFinished")
    public String processFinished(){
        return "/funding_process/process_finished";
    }

}
