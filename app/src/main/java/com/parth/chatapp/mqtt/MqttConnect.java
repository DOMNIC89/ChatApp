package com.parth.chatapp.mqtt;

import android.content.Context;
import android.widget.Toast;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttConnect {
    
    private Context context;
    private MqttCallback callback;
    private MqttAndroidClient client;
    private String subscribeTopic;
    private static MqttConnect instance;


    private MqttConnect() {

    }

    public static MqttConnect getInstance() {
        if (instance == null) {
            instance = new MqttConnect();
        }
        return instance;
    }

    public void setMqttCallback(MqttCallback mqttCallback){
        this.callback = mqttCallback;
    }

    public void connectMqtt(final Context context, final String clientId) {
        if (clientId.isEmpty()) {
            return;
        }

        if (client != null && client.isConnected()) {
            return;
        }

        if (client == null) {
            client = new MqttAndroidClient(context, "tcp://iot.eclipse.org:1883", clientId);
        }

        MqttConnectOptions conOpt = new MqttConnectOptions();
        conOpt.setUserName(clientId);
        conOpt.setCleanSession(true);
        conOpt.setKeepAliveInterval(30);

        client.setCallback(callback);
        if (!client.isConnected()) {
            try {
                client.connect(conOpt, null, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Toast.makeText(context, "Mqtt Connected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    public void subscribe(String topic) {
        MqttConnect.this.subscribeTopic = topic;
        if (client != null && client.isConnected()) {
            try {
                client.subscribe(topic, 1);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    public IMqttToken publish(String topic, String message) {
        IMqttToken token;
        if (client != null && client.isConnected()) {
            try {
                token = client.publish(topic, message.getBytes(), 1, true);
                return token;
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



}
