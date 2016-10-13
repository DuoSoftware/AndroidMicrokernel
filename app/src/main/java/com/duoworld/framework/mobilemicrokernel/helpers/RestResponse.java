package com.duoworld.framework.mobilemicrokernel.helpers;

import com.duoworld.framework.mobilemicrokernel.helpers.AbstractRestObject;
import com.google.gson.Gson;


public class RestResponse {
    private String response;
    private int code;
    private String[] headers;

    public boolean isError(){
        return getCode() != 200;
    }

    public <T> T asJSON(Class<T> typeClass){
        T outData =null;

        try {
            Gson gson = new Gson();
            outData = gson.fromJson(this.response, typeClass);
        }catch (Exception e){
            e.printStackTrace();
        }

        return outData;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }
}