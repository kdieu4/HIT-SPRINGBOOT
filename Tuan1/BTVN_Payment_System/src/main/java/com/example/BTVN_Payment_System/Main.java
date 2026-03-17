package com.example.BTVN_Payment_System;

import com.example.BTVN_Payment_System.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.example");
        OrderService orderService = context.getBean(OrderService.class);

        orderService.processOrder("Diệu", "Laptop", 5000000);
        orderService.processOrder("Phương Anh", "Smartphone", 3000000);
    }
}
