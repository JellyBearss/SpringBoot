package com.jellybears.krowdpoping.funding_process.controller;

import com.jellybears.krowdpoping.common.exception.address.AddressSaveException;
import com.jellybears.krowdpoping.funding_process.model.dto.AddressDTO;
import com.jellybears.krowdpoping.funding_process.model.service.AddressService;
import com.jellybears.krowdpoping.funding_process.model.service.AddressServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funding_process")
@Slf4j
public class FundingProcessController {

    private final AddressServiceImpl addressService;

    @Autowired
    public FundingProcessController(AddressServiceImpl addressService){
        this.addressService = addressService;
    }
    @GetMapping("address")
    public String defaultAddress() { return "/funding_process/default_address"; }

    @PostMapping("saveAddress")
    public String saveAddress(@ModelAttribute AddressDTO addressDTO) throws AddressSaveException {
        log.info("Received AddressDTO: {}", addressDTO);

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
