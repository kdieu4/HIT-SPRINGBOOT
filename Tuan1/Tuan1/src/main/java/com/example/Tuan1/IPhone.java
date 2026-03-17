package com.example.Tuan1;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class IPhone implements Phone{
    @Override
    public void call() {
        System.out.println("Gọi điện bằng iPhone!");
    }
}
