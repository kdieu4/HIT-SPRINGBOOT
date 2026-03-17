package com.example.Tuan1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class User {
    private Phone phone;

    @Autowired
    public User(Phone phone){
        this.phone = phone;
    }

    public void makeCall(){
        phone.call();
    }
}
