package com.parth.chatapp.ably;

import com.parth.chatapp.AppSingleton;
import com.parth.chatapp.Values;
import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.types.AblyException;

public class AblySender {

    private static AblySender instance;
    private AblyRealtime ablyRealtime;

    private AblySender() throws AblyException {
        ablyRealtime = new AblyRealtime(Values.ABLY_KEY);
    }

    public static AblySender getInstance() throws AblyException {
        if (instance == null) {
            instance = new AblySender();
        }
        return instance;
    }

    public void sendMessage(String message) {
        Channel channel = ablyRealtime.channels.get(AppSingleton.INSTANCE.getCurentUserChat());
    }
}
