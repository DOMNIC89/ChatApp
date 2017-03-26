package com.parth.chatapp;

import android.support.multidex.MultiDexApplication;
import com.parth.chatapp.database.DBHelper;

public class ChatApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper.getInstance(this);
        AppSingleton.INSTANCE.setContext(this);
    }
}
