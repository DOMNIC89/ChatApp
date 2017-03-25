package com.parth.chatapp.presenter;

import com.parth.chatapp.AppSingleton;
import com.parth.chatapp.ably.AblySender;
import com.parth.chatapp.model.Chat;
import io.ably.lib.realtime.Channel;
import io.ably.lib.realtime.CompletionListener;
import io.ably.lib.types.AblyException;
import io.ably.lib.types.ErrorInfo;
import io.ably.lib.types.Message;

public class ConversationPresenterImpl implements ConversationPresenter {

    private ConversationPresenter.View view;

    public ConversationPresenterImpl(View view) {
        this.view = view;
    }

    @Override
    public void sendMessage(String message) {
        if (message != null && message.isEmpty()) {
            return;
        }
        final Chat chat = new Chat(AppSingleton.INSTANCE.getLoggedInUserName(), message, System.currentTimeMillis());
        chat.setChatStatus(Chat.SENDING);
        view.insertChat(chat);
        try {
            AblySender.getInstance().setCompletionListener(new CompletionListener() {
                @Override
                public void onSuccess() {
                    chat.setChatStatus(Chat.SENT);
                    view.updateChat(chat);
                }

                @Override
                public void onError(ErrorInfo reason) {
                    chat.setChatStatus(Chat.FAILED);
                    view.updateChat(chat);
                }
            });
            AblySender.getInstance().sendMessage(chat.toJSON());
        } catch (AblyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribeChannel(String channelName) {
        try {
            AblySender.getInstance().subscribeChannel(channelName, new Channel.MessageListener() {
                @Override
                public void onMessage(Message messages) {
                    String message = messages.data.toString();
                    Chat chat = new Chat();
                    view.insertChat(chat.fromJSON(message));
                }
            });
        } catch (AblyException e) {
            e.printStackTrace();
        }
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
}
