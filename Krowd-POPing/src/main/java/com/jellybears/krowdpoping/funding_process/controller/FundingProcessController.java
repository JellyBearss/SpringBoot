package com.jellybears.krowdpoping.funding_process.controller;

import com.jellybears.krowdpoping.common.exception.address.AddressSaveException;
import com.jellybears.krowdpoping.funding_process.model.dto.AddressDTO;
import com.jellybears.krowdpoping.funding_process.model.service.AddressService;
import com.jellybears.krowdpoping.funding_process.model.service.AddressServiceImpl;
import com.jellybears.krowdpoping.user.model.dto.RoleTypeDTO;
import com.jellybears.krowdpoping.user.model.dto.UserDTO;
import com.jellybears.krowdpoping.user.model.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/funding_process")
@Slf4j
public class FundingProcessController {

    private final AddressServiceImpl addressService;
    private final AuthenticationService authenticationService;

    @Autowired
    public FundingProcessController(AddressServiceImpl addressService, AuthenticationService authenticationService){
        this.addressService = addressService;
        this.authenticationService = authenticationService;
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
            AddressDTO defaultAddress = addressService.getDefaultAddress(String.valueOf(loggedInUser.getUser_code()));

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

        addressService.saveAddress(addressDTO);
        return "/funding_process/pay_reservation";
    }

    @GetMapping("product")
    public String detailedProduct(){
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
