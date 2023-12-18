package com.jellybears.krowdpoping.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
    public class EmailConfig {
    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean auth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean starttlsEnable;

    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private boolean starttlsRequired;

    @Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
    private int connectionTimeout;

    @Value("${spring.mail.properties.mail.smtp.timeout}")
    private int timeout;

    @Value("${spring.mail.properties.mail.smtp.writetimeout}")
    private int writeTimeout;

        @Bean
        public JavaMailSender javaMailSender() {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

            mailSender.setHost("smtp.google.com"); // 메일 도메인 서버 주소
            mailSender.setUsername("krowdpoping@gmail.com"); // 메일 유저 이름
            mailSender.setPassword("xbhepdbvnkuaczev"); // 메일 패스워드

            mailSender.setPort(587); // 메일 인증서버 포트

            mailSender.setJavaMailProperties(getMailProperties()); // 메일 인증서버 정보

            return mailSender;
        }

        private Properties getMailProperties() {
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", auth);
            properties.put("mail.smtp.starttls.enable", starttlsEnable);
            properties.put("mail.smtp.starttls.required", starttlsRequired);
            properties.put("mail.smtp.connectiontimeout", connectionTimeout);
            properties.put("mail.smtp.timeout", timeout);
            properties.put("mail.smtp.writetimeout", writeTimeout);
            return properties;
        }
    }

