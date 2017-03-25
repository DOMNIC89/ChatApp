package com.parth.chatapp.presenter;

import com.parth.chatapp.AppSingleton;

public class MainActivityPresenterImpl implements MainActivityPresenter {

    private MainActivityPresenter.View view;

    public MainActivityPresenterImpl(View view) {
        this.view = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void handleOnButtonClicked(String userName) {
        if (!isValid(userName)) {
            view.onInvalidInput();
            return;
        }
        AppSingleton.INSTANCE.setLoggedInUserName(userName);
        view.moveToNextScreen(userName);
    }

    @Override
    public void checkIfUserExists(String userName) {
        if (isValid(userName)) {
            AppSingleton.INSTANCE.setLoggedInUserName(userName);
            view.moveToNextScreen(userName);
        }
    }

    private boolean isValid(String userName) {
        return userName != null && userName.trim().length() > 0;
    }
}
