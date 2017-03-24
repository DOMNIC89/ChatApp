package com.parth.chatapp.presenter;

public interface MainActivityPresenter extends BasePresenter {

    void handleOnButtonClicked(String userName);

    interface View {
        void onInvalidInput();

        void moveToNextScreen(String name);
    }
}
