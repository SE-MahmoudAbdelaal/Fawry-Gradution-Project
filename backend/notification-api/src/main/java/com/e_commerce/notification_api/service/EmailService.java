package com.e_commerce.notification_api.service;

public interface EmailService {
    public void sendEmail(String to, String subject, String text) ;
    }
