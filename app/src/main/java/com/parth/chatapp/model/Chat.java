package com.parth.chatapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.IntDef;
import com.parth.chatapp.AppSingleton;
import com.parth.chatapp.database.DBHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class Chat {

    public static final String USER_NAME = "username";
    public static final String MESSAGE = "message";
    public static final String TIMESTAMP = "timestamp";
    public static final String TO = "to";
    public static final String CHATSTATUS = "chatstatus";
    public static final String CHAT_TABLE_NAME = "chattable";
    public static final String CHAT_TABLE =
        "CREATE TABLE " + CHAT_TABLE_NAME + " (" + MESSAGE + " TEXT, " + USER_NAME + " TEXT, " + CHATSTATUS + " "
            + "INTEGER, " + TIMESTAMP + " DATETIME, `" + TO + "` TEXT)";
    public static final String[] CHAT_PROJECTION = new String[] {
        MESSAGE, TIMESTAMP, USER_NAME, CHATSTATUS
    };

    public static final int SENT = 1;
    public static final int FAILED = 0;
    public static final int SENDING = -1;

    public static final int SELF = 0;
    public static final int COUNTERPART = 1;

    private String userName;
    private String to;
    private String from;
    private String message;
    private long timestamp;
    private
    @ChatStatus
    int chatStatus;

    public Chat(String userName, String message, long timestamp) {
        this.userName = userName;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Chat() {
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

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
        contentValues.put(TO, to);
        DBHelper.getInstance(context).insert(CHAT_TABLE_NAME, contentValues);
    }

    public void update(Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MESSAGE, message);
        contentValues.put(TIMESTAMP, timestamp);
        contentValues.put(USER_NAME, userName);
        contentValues.put(CHATSTATUS, chatStatus);
        contentValues.put(TO, to);
        StringBuilder whereBuilder = new StringBuilder();
        whereBuilder.append(USER_NAME).append(" = ?");
        whereBuilder.append(" and ");
        whereBuilder.append(TIMESTAMP).append("= ?");
        String[] args = new String[] { userName, String.valueOf(timestamp) };
        DBHelper.getInstance(context).update(CHAT_TABLE_NAME, contentValues, whereBuilder.toString(), args);
    }

    public static List<Chat> getAllChatsFromUser(Context context, String currentUserChat) {
        final String where = String.format(Locale.ENGLISH, "%s = \"%s\" OR `%s` = \"%s\"", USER_NAME, currentUserChat, TO, currentUserChat);
        final String orderBy = String.format(Locale.ENGLISH, "%s ASC", TIMESTAMP);
        Cursor cursor = DBHelper.getInstance(context).query(CHAT_TABLE_NAME, CHAT_PROJECTION, where, orderBy);
        List<Chat> list = new ArrayList<>();
        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++, cursor.moveToNext()) {
                String message = cursor.getString(cursor.getColumnIndex(MESSAGE));
                String userName = cursor.getString(cursor.getColumnIndex(USER_NAME));
                @ChatStatus
                int chatStatus = cursor.getInt(cursor.getColumnIndex(CHATSTATUS));
                long timestamp = cursor.getLong(cursor.getColumnIndex(TIMESTAMP));
                Chat chat = new Chat(userName, message, timestamp);
                chat.setChatStatus(chatStatus);
                list.add(chat);
            }
            if (cursor.isClosed()) {
                cursor.close();
            }
        }
        return list;
    }

    public static List<String> getUsers(Context context) {
        String query = String.format(Locale.ENGLISH, "select DISTINCT %s, MAX(%s) FROM %s GROUP BY %s ORDER BY %s ASC, %s", USER_NAME,
            TIMESTAMP, CHAT_TABLE_NAME, USER_NAME, TIMESTAMP, USER_NAME);
        Cursor cursor = DBHelper.getInstance(context).rawQuery(query, null);
        List<String> list = new ArrayList<>();
        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++, cursor.moveToNext()) {
                String userName = cursor.getString(cursor.getColumnIndex(USER_NAME));
                if (!userName.equals(AppSingleton.INSTANCE.getLoggedInUserName())) {
                    list.add(userName);
                }
            }
        }
        return list;
    }

    public static Chat getLastChat(Context context, String userName) {
        List<Chat> chatList = getAllChatsFromUser(context, userName);
        return chatList.get(chatList.size() - 1);
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(USER_NAME, userName);
            jsonObject.put(MESSAGE, message);
            jsonObject.put(TIMESTAMP, timestamp);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public Chat fromJSON(String messageReceived) {
        try {
            JSONObject jsonObject = new JSONObject(messageReceived);
            this.message = jsonObject.getString(MESSAGE);
            this.timestamp = jsonObject.getLong(TIMESTAMP);
            this.userName = jsonObject.getString(USER_NAME);
            return this;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @IntDef({ SENT, FAILED, SENDING })
    @interface ChatStatus {
    }

    @IntDef({ SELF, COUNTERPART })
    @interface DIRECTION {
    }
}
