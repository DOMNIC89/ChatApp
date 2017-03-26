package com.parth.chatapp;

import android.content.Context;

public enum AppSingleton {

    INSTANCE;

    private String userName;
    private String loggedInUserName;
    private Context context;

    public void currentUserChat(String userName) {
        this.userName = userName;
    }

    /**
     * This method will accept the application context to avoid the memory leak
     * */
    public void setContext(Context context) {
        this.context = context;
    }

    public String getCurentUserChat() {
        return userName;
    }

    public void setLoggedInUserName(String loggedInUserName) {
        this.loggedInUserName = loggedInUserName;
    }

    public String getLoggedInUserName() {
        if (loggedInUserName == null && loggedInUserName.length() == 0) {
            loggedInUserName = Preferences.getUserName(context);
        }
        return loggedInUserName;
    }
}
