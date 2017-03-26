package com.parth.chatapp.mqtt;

import com.parth.chatapp.model.Chat;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

class ChatAppMqttCallback implements MqttCallback {

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String msg = new String(message.getPayload());
        Chat chat = new Chat();
        chat.fromJSON(msg);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
