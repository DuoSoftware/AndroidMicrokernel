package com.duoworld.framework.mobilemicrokernel.helpers;

public abstract class AbstractRestObject {
    private boolean isError = false;
    private String errorMessage = null;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
