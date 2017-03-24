package com.parth.chatapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.parth.chatapp.widget.InputDialog;

public class InboxActivity extends AppCompatActivity implements InputDialog.OnInputDialogListener {

    private static final String ARG_NAME = "arg_name";

    public static void launch(Context context, String name) {
        Intent intent = new Intent(context, InboxActivity.class);
        intent.putExtra(ARG_NAME, name);
        context.startActivity(intent);
    }

    private RecyclerView rv_userChatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        rv_userChatList = (RecyclerView) findViewById(R.id.user_chat_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_inbox, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_chat_user) {
            showAddUserDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAddUserDialog() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("input_dialog");
        if (fragment == null) {
            InputDialog.getInstance().show(getSupportFragmentManager(), "input_dialog");
        }
    }

    @Override
    public void onPositiveButtonClicked(String name) {
        if (!TextUtils.isEmpty(name)) {
            ConversationActivity.launch(this, name);
        }
    }

    @Override
    public void onCancelButtonClicked() {

    }
}
