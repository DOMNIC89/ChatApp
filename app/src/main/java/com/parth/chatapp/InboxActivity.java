package com.parth.chatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class InboxActivity extends AppCompatActivity {

    private RecyclerView rv_userChatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        rv_userChatList = (RecyclerView) findViewById(R.id.user_chat_list);

    }
}
