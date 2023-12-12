package com.jellybears.krowdpoping.funding_process.controller;

import com.jellybears.krowdpoping.funding_process.model.dto.AddressDTO;
import com.jellybears.krowdpoping.funding_process.model.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("funding_process/*")
public class FundingProcessController {

    private final AddressService addressService;
    @Autowired
    public FundingProcessController(AddressService addressService){
        this.addressService = addressService;
    }
    @GetMapping("address")
    public String defaultAddress(Model model) {
        model.addAttribute("AddressDTO", new AddressDTO());
        return "/funding_process/default_address";
    }

    public String saveAddress(AddressDTO addressDTO) {
        addressService.saveAddress(addressDTO);
        return "redirect:/";
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
