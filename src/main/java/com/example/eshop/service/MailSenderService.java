package com.example.eshop.service;

import com.example.eshop.domain.User;

public interface MailSenderService {
    void sendActivateCode(User user);
}
