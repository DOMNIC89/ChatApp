package com.parth.chatapp.presenter;

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
        view.moveToNextScreen(userName);
    }

    private boolean isValid(String userName) {
        if (userName != null && userName.length() > 0) {
            return true;
        }
        return false;
    }
}
