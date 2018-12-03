package com.example.igor.messanger;

public class LeftMessage extends ListItem {

    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public int getType() {
        return TYPE_LEFT_MESSAGE;
    }
}
