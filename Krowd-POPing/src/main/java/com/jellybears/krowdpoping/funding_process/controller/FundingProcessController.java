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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "/funding_process/default_address";
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
