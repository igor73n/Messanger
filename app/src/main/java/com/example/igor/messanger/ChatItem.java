package com.example.igor.messanger;

public class ChatItem {

    private String collocuter;
    private String lastMessage;
    private String data;


    public ChatItem(String collocuter, String lastMessage, String data) {

      this.collocuter = collocuter;
      this.lastMessage = lastMessage;
      this.data = data;
    }

      public String getCollocuter() {
          return this.collocuter;
        }

        public void setCollocuter(String collocuter) {
            this.collocuter = collocuter;
        }

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

    }












