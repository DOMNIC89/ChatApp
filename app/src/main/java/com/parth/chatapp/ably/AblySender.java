package com.parth.chatapp.ably;

import com.parth.chatapp.AppSingleton;
import com.parth.chatapp.Values;
import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.realtime.CompletionListener;
import io.ably.lib.types.AblyException;
import io.ably.lib.types.Message;
import org.json.JSONObject;

public class AblySender {

    private static AblySender instance;
    private AblyRealtime ablyRealtime;
    private CompletionListener listener;

    private AblySender() throws AblyException {
        ablyRealtime = new AblyRealtime(Values.ABLY_KEY);
    }

    public static AblySender getInstance() throws AblyException {
        if (instance == null) {
            instance = new AblySender();
        }
        return instance;
    }

    public void subscribeChannel(String channelPath, Channel.MessageListener messageListener) throws AblyException {
        Channel channel = ablyRealtime.channels.get(channelPath);
        channel.subscribe(messageListener);
    }

    public void sendMessage(JSONObject jsonObject) throws AblyException {
        Channel channel = ablyRealtime.channels.get(AppSingleton.INSTANCE.getCurentUserChat());
        Message message = new Message("chat", jsonObject.toString());
        channel.publish("publish", message, listener);
    }

    public void setCompletionListener(CompletionListener listener) {
        this.listener = listener;
    }
}
