package com.duoworld.framework.mobilemicrokernel.core.ceb;

public class CebResponseConnect {
    private boolean error;
    private String message;


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CebResponseConnect(boolean isError, String message){
        this.error = isError;
        this.message = message;
    }
}
