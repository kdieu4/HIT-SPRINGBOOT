package com.example.Tuan1;

import org.springframework.stereotype.Component;

@Component
public class Samsung implements Phone{
    @Override
    public void call() {
        System.out.println("Gọi điện bằng Samsung!");
    }
}
