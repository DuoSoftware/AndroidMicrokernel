package com.duoworld.framework.mobilemicrokernel;

import com.duoworld.framework.mobilemicrokernel.core.ceb.CebResponseConnect;

public class MicrokernelResponseAttached {
    private boolean error;
    private String message;

    public boolean getError() {
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

    public MicrokernelResponseAttached(CebResponseConnect response){
        this.error = response.isError();
        this.message = response.getMessage();
    }
}
