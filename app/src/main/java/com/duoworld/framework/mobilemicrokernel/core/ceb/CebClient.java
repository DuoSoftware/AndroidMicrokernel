package com.duoworld.framework.mobilemicrokernel.core.ceb;

import com.duoworld.framework.mobilemicrokernel.MicrokernelUrls;
import com.duoworld.framework.mobilemicrokernel.core.auth.AuthResponseLogin;
import com.github.nkzawa.emitter.Emitter.Listener;
import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.socketio.client.IO;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class CebClient {

    private Socket socket;
    private AuthResponseLogin authData;

    private CebListenerConnected onConnectListener;
    
    public void Register(AuthResponseLogin authData){
        this.authData = authData;

        boolean isError = true;
        String message = null;
        try {
            socket = IO.socket(MicrokernelUrls.CEB);
        } catch (URISyntaxException e) {
            message = e.getMessage();
        }

        socket.on(Socket.EVENT_CONNECT, onConnect);
        socket.on(Socket.EVENT_DISCONNECT, onDisconnect);
        socket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        socket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        socket.on("connected", onLogin);
        socket.on("message", onMessage);
        socket.connect();

        if (onConnectListener !=null && isError)
            onConnectListener.OnRegistered(new CebResponseConnect(isError, message));
    }

    private void subscribeOrUnsubscribe(String what, String name){
        CebMessage<CebRequestEventSubscribe> message = new CebMessage<>(name, "event-" + what, authData.getSecurityToken(), new CebRequestEventSubscribe(authData.getUsername(), name));
        emit("command", message, new Ack() {
            @Override
            public void call(Object... args) {

            }
        });
    }

    public void SubscribeEvent(String name){
        this.subscribeOrUnsubscribe("subscribe", name);
    }

    public void UnsubscribeEvent(String name){
        this.subscribeOrUnsubscribe("unsubscribe", name);
    }

    public <T> void TriggerEvent (String name, T o){
        CebMessage<T> message = new CebMessage<>(name, "event", authData.getSecurityToken(), o);

        emit("command", message, new Ack() {
            @Override
            public void call(Object... args) {

            }
        });
    }

    public void Unregister(){
        socket.disconnect();
        socket.off("message", onMessage);
        socket.off("connected", onLogin);
        socket.off(Socket.EVENT_CONNECT, onConnect);
        socket.off(Socket.EVENT_DISCONNECT, onDisconnect);
        socket.off(Socket.EVENT_CONNECT_ERROR, onConnectError);
        socket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
    }

    private Listener onMessage = new Listener() {
        @Override
        public void call(Object... args) {
        }
    };

    private Listener onLogin = new Listener() {
        @Override
        public void call(Object... args) {
        }
    };

    private void emit(String name, Object o, Ack a){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject((new Gson()).toJson(o));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        socket.emit(name, jsonObject, a);
    }

    private Listener onConnect = new Listener() {
        @Override
        public void call(Object... args) {
            CebRequestRegister req = new CebRequestRegister(authData.getUsername(), authData.getSecurityToken(), authData.getName(), authData.getUserID(), "user");

            emit("register", req, new Ack() {
                @Override
                public void call(Object... args) {
                    onConnectListener.OnRegistered(new CebResponseConnect(false, "Successfully Registered"));
                    if (args[0] != null) {
                    } else {
                    }
                }
            });
        }
    };

    private Listener onDisconnect = new Listener() {
        @Override
        public void call(Object... args) {
        }
    };

    private Listener onConnectError = new Listener() {
        @Override
        public void call(Object... args) {
        }
    };

    public void setOnConnectListener(CebListenerConnected onConnectListener) {
        this.onConnectListener = onConnectListener;
    }
}
