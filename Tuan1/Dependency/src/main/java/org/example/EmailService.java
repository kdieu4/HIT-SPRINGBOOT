package org.example;

public class EmailService implements MessageService{
    public void sendMessage(String message){
        System.out.println("Send Email: " + message);
    }
}
