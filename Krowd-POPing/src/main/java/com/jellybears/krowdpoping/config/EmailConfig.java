package com.jellybears.krowdpoping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class EmailConfig {
    @Bean
    public JavaMailSender javaMailSender(){
        Properties mailProperties = new Properties();
        mailProperties.put("mail.transport.protocol","smtp");
        mailProperties.put("mail.smtp.auth","true");
        mailProperties.put("mail.smtp.starttls.enable","true");
        mailProperties.put("mail.smtp.debug","true");
        mailProperties.put("mail.smtp.ssl.trust","smtp.gmail.com");
        mailProperties.put("mail.smtp.ssl.protocols","TLSv1.2");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("krowdpoping@gmail.com");
        mailSender.setPassword("xbhe pdbv nkua czev");
        mailSender.setDefaultEncoding("UTF-8");

        return mailSender;
    }
}
