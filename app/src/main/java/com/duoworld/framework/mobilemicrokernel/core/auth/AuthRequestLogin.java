package com.duoworld.framework.mobilemicrokernel.core.auth;

public class AuthRequestLogin {
    private String userName;
    private String password;
    private String domainName;

    public AuthRequestLogin(String userName, String password, String domainName){
        this.setUserName(userName);
        this.setPassword(password);
        this.setDomainName(domainName);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
}