package com.duoworld.framework.mobilemicrokernel.core.ceb;

public class CebMessage<T> {
    private String name;
    private String type;
    private T data;
    private String token;

    public CebMessage(String name, String type, String token, T data){
        this.name = name;
        this.type = type;
        this.data = data;
        this.token = token;
    }


}
