package com.jellybears.krowdpoping.user.model.service;


import org.springframework.stereotype.Service;


@Service

public interface EmailService {
    String sendSimpleMessage(String to)throws Exception;
}

