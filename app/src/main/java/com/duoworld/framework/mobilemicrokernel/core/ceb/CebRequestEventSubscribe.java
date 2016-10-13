package com.duoworld.framework.mobilemicrokernel.core.ceb;

public class CebRequestEventSubscribe {
    public String username;
    public String event;

    public CebRequestEventSubscribe(String username, String event){
        this.username = username;
        this.event = event;
    }
}
