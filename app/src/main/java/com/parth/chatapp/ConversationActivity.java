package com.parth.chatapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.parth.chatapp.presenter.ConversationPresenter;
import com.parth.chatapp.presenter.ConversationPresenterImpl;

public class ConversationActivity extends AppCompatActivity implements ConversationPresenter.View{

    private static final String ARG_USER_NAME = "arg_user_name";
    private RecyclerView rv_chatList;
    private EditText et_composer;
    private ImageView iv_send;

    private ConversationPresenter presenter;

    private View.OnClickListener sendClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String message= et_composer.getText().toString();
            presenter.sendMessage(message);
        }
    };

    public static void launch(Context context, String nameOfUser) {
        Intent intent = new Intent(context, ConversationActivity.class);
        intent.putExtra(ARG_USER_NAME, nameOfUser);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        presenter = new ConversationPresenterImpl(this);
        rv_chatList = (RecyclerView) findViewById(R.id.recyclerView);
        et_composer = (EditText) findViewById(R.id.compose_et);
        iv_send = (ImageView) findViewById(R.id.send);

    }
}
