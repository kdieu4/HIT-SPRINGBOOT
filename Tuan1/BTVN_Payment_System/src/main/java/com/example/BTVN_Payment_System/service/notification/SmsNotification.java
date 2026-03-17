package com.example.BTVN_Payment_System.service.notification;

import org.springframework.stereotype.Component;

@Component
public class SmsNotification implements INotificationService {
    @Override
    public void sendNotification(String to, String message) {
        System.out.println("Gửi sms: " + to + "\nThông báo: " + message);
    }
}
