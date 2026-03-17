package com.example.BTVN_Payment_System.service;

import com.example.BTVN_Payment_System.service.notification.INotificationService;
import com.example.BTVN_Payment_System.service.payment.IPaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    private IPaymentMethod paymentMethod;

    private INotificationService notificationService;

    public OrderService() {
    }

    @Autowired
    public OrderService(IPaymentMethod paymentMethod, INotificationService notificationService) {
        this.paymentMethod = paymentMethod;
        this.notificationService = notificationService;
    }

    public void processOrder(String customer, String product, double amount) {
        String notification = "Bạn đã thanh toán " + product + " trị giá " + amount + " thành công bằng " + paymentMethod.getMethodName() + "!";
        paymentMethod.pay(amount);
        notificationService.sendNotification(customer, notification);
    }

}
