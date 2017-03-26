package com.parth.chatapp.presenter;

import com.parth.chatapp.AppSingleton;
import com.parth.chatapp.model.Chat;
import com.parth.chatapp.mqtt.MqttConnect;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ConversationPresenterImpl implements ConversationPresenter, MqttCallbackExtended {

    private ConversationPresenter.View view;
    private List<IMqttToken> tokenList = new ArrayList<>();

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
        chat.setTo(AppSingleton.INSTANCE.getCurentUserChat());
        view.insertChat(chat);
        String topic = AppSingleton.INSTANCE.getCurentUserChat() + "/" + AppSingleton.INSTANCE.getLoggedInUserName();
        IMqttToken token = MqttConnect.getInstance().publish(topic, chat.toJSON().toString());
        if (token != null) {
            tokenList.add(token);
        }
    }

    @Override
    public void subscribeChannel(String channelName) {
        MqttConnect.getInstance().subscribe(channelName);
    }

    @Override
    public void resume() {
        MqttConnect.getInstance().setMqttCallback(this);
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
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String msg = new String(message.getPayload());
        Chat chat = new Chat();
        chat.setTo(AppSingleton.INSTANCE.getCurentUserChat());
        view.insertChat(chat.fromJSON(msg));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        tokenList.contains(token);
        Chat chat = new Chat();
        try {
            chat.fromJSON(new String(token.getMessage().getPayload()));
            view.updateChat(chat);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        StringBuilder channelName = new StringBuilder();
        channelName.append(AppSingleton.INSTANCE.getLoggedInUserName()).append("/").append(AppSingleton.INSTANCE.getCurentUserChat());
        subscribeChannel(channelName.toString());
    }
}
