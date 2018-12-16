package controller.Server;


import java.util.LinkedList;
import java.util.List;

public class ClientMain {
    String messageSender;
    String message;
    Boolean biggerText;

    public ClientMain(){

    }

    public ClientMain(String messageSender, String message, Boolean biggerText) {
        this.messageSender = messageSender;
        this.message = message;
        this.biggerText = biggerText;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getBiggerText() {
        return biggerText;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setBiggerText(Boolean biggerText) {
        this.biggerText = biggerText;
    }


}
