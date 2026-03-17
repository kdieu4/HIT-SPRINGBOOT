package org.example;

public class Client implements InjectionMessage{
    private MessageService messageService;

    public Client() {
    }

    public Client(MessageService messageServiceParam){
        this.messageService = messageServiceParam;
    }

//    public void setMessageService(MessageService messageService) {
//        this.messageService = messageService;
//    }

    public void processMessage(String message) {
        messageService.sendMessage(message);
    }

    @Override
    public void setService(MessageService messageService) {
        this.messageService = messageService;
    }
}
