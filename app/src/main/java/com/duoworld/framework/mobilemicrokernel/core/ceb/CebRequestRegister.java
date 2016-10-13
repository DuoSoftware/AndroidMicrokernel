package com.duoworld.framework.mobilemicrokernel.core.ceb;

public class CebRequestRegister {
    private String userName;
    private String securityToken;
    private String fullName;
    private String userId;
    private String tenantId;
    private String resourceClass;


    public CebRequestRegister(String userName, String securityToken, String fullName, String userId, String resourceClass){
        this.userName = userName;
        this.securityToken = securityToken;
        this.fullName = fullName;
        this.userId = userId;
        this.resourceClass = resourceClass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getResourceClass() {
        return resourceClass;
    }

    public void setResourceClass(String resourceClass) {
        this.resourceClass = resourceClass;
    }
}
