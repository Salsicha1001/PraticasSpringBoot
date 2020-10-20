package com.example.custom.config;

import com.example.custom.service.EmailService;
import com.example.custom.service.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevConfig {
    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
