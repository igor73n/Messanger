package com.example.igor.messanger;

public class RightMessage extends ListItem {

    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public int getType() {
        return TYPE_RIGHT_MESSAGE;
    }
}
