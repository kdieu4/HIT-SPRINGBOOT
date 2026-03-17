package com.example.BTVN_Payment_System.service.notification;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EmailNotification implements INotificationService {
    @Override
    public void sendNotification(String to, String message) {
        System.out.println("Gửi email: " + to + "\nThông báo: " + message);
    }
}
