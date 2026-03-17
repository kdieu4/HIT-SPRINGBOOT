package org.example.Bai2;

public class User {
    private Phone  phone;

    public User(Phone phone) {
        this.phone = phone;
    }

    public void makeCall(){
        phone.call();
    }
}

