package com.parth.chatapp.presenter;

import com.parth.chatapp.model.Chat;

public interface ConversationPresenter extends BasePresenter {

    void sendMessage(String message);

    void subscribeChannel(String channelName);

    interface View {

        void insertChat(Chat chat);

        void updateChat(Chat chat);
    }

}
