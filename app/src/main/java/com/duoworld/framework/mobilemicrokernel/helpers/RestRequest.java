package com.duoworld.framework.mobilemicrokernel.helpers;

public class RestRequest {
    private String url;
    private String[] headers;

    public RestRequest(String url, String[] headers){
        this.url = url;
        this.headers = headers;
    }

    public String getUrl() {
        return url;
    }

    public String[] getHeaders() {
        return headers;
    }
}
