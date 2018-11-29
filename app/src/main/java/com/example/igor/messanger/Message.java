package com.example.igor.messanger;

public class Message {


        private String sender,message,data;

    public Message(String message, String sender, String data){
        this.message = message;
        this.sender = sender;
        this.data = data;
    }


        public String getMessage() { return this.message; }

        public void setMessage(String message) { this.message = message; }

        public String getSender() {
        return this.sender;
    }

        public void setSender(String sender) {
        this.sender = sender;
    }

        public String getData() {
        return this.data;
    }

        public void setData(String data) {
        this.data = data;
    }


    }
