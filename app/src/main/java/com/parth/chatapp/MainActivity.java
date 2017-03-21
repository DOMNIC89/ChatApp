package com.parth.chatapp;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import com.parth.chatapp.presenter.MainActivityPresenter;
import com.parth.chatapp.presenter.MainActivityPresenterImpl;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View{

    private TextInputLayout til_userNameContainer;
    private TextInputEditText tiet_userName;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenterImpl(this);
        Button btn_submit = (Button) findViewById(R.id.submit);
        til_userNameContainer = (TextInputLayout) findViewById(R.id.user_name);
        tiet_userName = (TextInputEditText) findViewById(R.id.user_name_et);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.handleOnButtonClicked(tiet_userName.getText().toString());
            }
        });
        tiet_userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (til_userNameContainer.isErrorEnabled()) {
                    til_userNameContainer.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onInvalidInput() {
        til_userNameContainer.setErrorEnabled(true);
        til_userNameContainer.setError("Invalid Username");
    }

    @Override
    public void moveToNextScreen() {
        // TODO: 17/3/17 Add next screen
    }
}
