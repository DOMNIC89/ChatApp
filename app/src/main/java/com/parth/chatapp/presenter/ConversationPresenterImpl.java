package com.parth.chatapp.presenter;

public class ConversationPresenterImpl implements ConversationPresenter {

    private ConversationPresenter.View view;

    public ConversationPresenterImpl(View view) {
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
    public void sendMessage(String message) {
        if (message != null && message.isEmpty()) {
            return;
        }

    }
}
