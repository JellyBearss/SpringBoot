package com.jellybears.krowdpoping.funding_process.controller;

import com.jellybears.krowdpoping.funding_process.model.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("funding_process/*")
public class FundingProcessController {

    @GetMapping("address")
    public String defaultAddress() { return "/funding_process/address"; }

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
