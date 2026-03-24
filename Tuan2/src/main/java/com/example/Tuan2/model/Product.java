package com.example.Tuan2.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class Product {
    @Getter
    @Setter
    private long id;
    @Setter
    @Getter
    private String name;

    public Product() {
    }

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
