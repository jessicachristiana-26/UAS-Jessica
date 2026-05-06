package com.example.registrasi._0420230009.config;

import org.springframework.stereotype.Service;

@Service
public class CaptchaService {
    public String generateCaptcha(){
        return ""+(int)(Math.random()*9000+1000);
    }
}