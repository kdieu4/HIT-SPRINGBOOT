package org.example.Bai2;

public class Main {
    public static void main(String[] args) {
        Phone phone = new Samsung();
        User user = new User(phone);
        user.makeCall();
    }
}
