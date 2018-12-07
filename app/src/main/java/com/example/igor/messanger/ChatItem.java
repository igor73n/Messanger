package com.example.igor.messanger;

public class ChatItem {

    private String collocuter, lastMessage, data, id;


    public ChatItem(String collocuter, String lastMessage, String data, String id) {

      this.collocuter = collocuter;
      this.lastMessage = lastMessage;
      this.data = data;
      this.id = id;
    }

    public String getCollocuter() { return this.collocuter; }

    public void setCollocuter(String collocuter) { this.collocuter = collocuter; }

    public String getLastMessage() {
        return this.lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    }












