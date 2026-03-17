package com.example.BTVN_Payment_System.service.payment;

import org.springframework.stereotype.Component;

@Component
public class CashPayment implements IPaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("Thu ngân đã nhận " + amount + "đ tiền mặt");
    }

    @Override
    public String getMethodName() {
        return "Tiền mặt";
    }
}
