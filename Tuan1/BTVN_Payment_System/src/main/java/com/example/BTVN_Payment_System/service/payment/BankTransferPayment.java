package com.example.BTVN_Payment_System.service.payment;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BankTransferPayment implements IPaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Đang tạo mã QR ngân hàng... Đã thanh toán " + amount + "đ thành công");
    }

    @Override
    public String getMethodName() {
        return "Chuyển khoản Ngân Hàng";
    }
}
