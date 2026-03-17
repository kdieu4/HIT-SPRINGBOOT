package com.example.BTVN_Payment_System.service.payment;

public interface IPaymentMethod {
    void pay(double amount);
    String getMethodName();
}
