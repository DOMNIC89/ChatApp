package com.parth.chatapp;

public enum AppSingleton {

    INSTANCE;

    private String userName;
    private String loggedInUserName;

    public void currentUserChat(String userName) {
        this.userName = userName;
    }

    public String getCurentUserChat() {
        return userName;
    }

    public void setLoggedInUserName(String loggedInUserName) {
        this.loggedInUserName = loggedInUserName;
    }

    public String getLoggedInUserName() {
        return loggedInUserName;
    }
}
