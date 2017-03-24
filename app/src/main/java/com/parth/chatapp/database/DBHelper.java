package com.parth.chatapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.parth.chatapp.model.Chat;
import com.parth.chatapp.model.User;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "chat_app_sql";
    private static final int DB_VERSION = 1;
    private static DBHelper dbHelper;

    public static DBHelper getInstance (Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        }
        return dbHelper;
    }

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Chat.CHAT_TABLE);
        db.execSQL(User.USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Chat.CHAT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + User.USER_TABLE_NAME);
    }

    public long insert(String tableName, ContentValues contentValues) {
        return getWritableDatabase().insert(tableName, null, contentValues);
    }
}
