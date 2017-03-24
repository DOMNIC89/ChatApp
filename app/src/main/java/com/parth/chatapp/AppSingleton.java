package com.parth.chatapp;

public enum AppSingleton {

    INSTANCE;

    private String userName;

    public void currentUserChat(String userName) {
        this.userName = userName;
    }

    public String getCurentUserChat() {
        return userName;
    }
}
