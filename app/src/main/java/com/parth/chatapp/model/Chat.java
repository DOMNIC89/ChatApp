package com.parth.chatapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.IntDef;
import com.parth.chatapp.database.DBHelper;

public class Chat {

    public static final String USER_NAME = "username";
    public static final String MESSAGE = "message";
    public static final String TIMESTAMP = "timestamp";
    public static final String CHATSTATUS = "chatstatus";
    public static final String CHAT_TABLE_NAME = "chattable";
    public static final String CHAT_TABLE = "CREATE TABLE "+CHAT_TABLE_NAME+" ("+MESSAGE+" TEXT, " + USER_NAME +" TEXT, " + CHATSTATUS+" "
        + "INTEGER, " + TIMESTAMP +" DATETIME)";

    public static final int SENT = 1;
    public static final int FAILED  = 0;
    public static final int SENDING = -1;

    private String userName;
    private String message;
    private long timestamp;
    private @ChatStatus int chatStatus;

    public Chat(String userName, String message, long timestamp) {
        this.userName = userName;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getChatStatus() {
        return chatStatus;
    }

    public void setChatStatus(@ChatStatus int chatStatus) {
        this.chatStatus = chatStatus;
    }

    public void insertChat(Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MESSAGE, message);
        contentValues.put(TIMESTAMP, timestamp);
        contentValues.put(USER_NAME, userName);
        contentValues.put(CHATSTATUS, chatStatus);
        DBHelper.getInstance(context).insert(CHAT_TABLE_NAME, contentValues);
    }

    @IntDef({SENT, FAILED, SENDING})
    @interface ChatStatus {
    }
}
