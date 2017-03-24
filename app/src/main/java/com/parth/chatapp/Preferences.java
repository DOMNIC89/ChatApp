package com.parth.chatapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private static final String FILE_NAME = "chat_app_preferences";
    private static final String USER_NAME = "user_name";

    public static SharedPreferences getSharedPreferences (Context context) {
        return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static void setUserName(Context context, String userName) {
        getSharedPreferences(context).edit().putString(USER_NAME, userName).apply();
    }

    public static String getUserName(Context context) {
        return getSharedPreferences(context).getString(USER_NAME, "");
    }
}
