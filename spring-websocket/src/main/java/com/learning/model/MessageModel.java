package com.learning.model;

public class MessageModel {

    private String sender;
    private String text;

    @Override
    public String toString() {
        return "MessageModel{" +
                "sender='" + sender + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
