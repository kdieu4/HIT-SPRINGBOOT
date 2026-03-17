package com.example.BTVN_Payment_System.service.payment;

import org.springframework.stereotype.Component;

@Component
public class MoMoPayment implements IPaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Đang kết nối với MoMo.. Đã thanh toán " + amount + "đ thành công");
    }

    @Override
    public String getMethodName() {
        return "Ví điện tử MoMo";
    }
}
