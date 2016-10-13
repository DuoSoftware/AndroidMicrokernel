package com.duoworld.framework.mobilemicrokernel.core.auth;

import com.duoworld.framework.mobilemicrokernel.helpers.AbstractRestObject;

import java.util.HashMap;

public class AuthResponseLogin extends AbstractRestObject{

  private String UserID;
  private String Username;
  private String Name;
  private String Email;
  private String SecurityToken;
  private String Domain;
  private String DataCaps;
  private String ClientIP;
  private HashMap<String, String> Otherdata;

  public String getUserID() {
    return UserID;
  }

  public void setUserID(String userID) {
    this.UserID = userID;
  }

  public String getUsername() {
    return Username;
  }

  public void setUsername(String username) {
    this.Username = username;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    this.Name = name;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String email) {
    this.Email = email;
  }

  public String getSecurityToken() {
    return SecurityToken;
  }

  public void setSecurityToken(String securityToken) {
    this.SecurityToken = securityToken;
  }

  public String getDomain() {
    return Domain;
  }

  public void setDomain(String domain) {
    this.Domain = domain;
  }

  public String getDataCaps() {
    return DataCaps;
  }

  public void setDataCaps(String dataCaps) {
    this.DataCaps = dataCaps;
  }

  public String getClientIP() {
    return ClientIP;
  }

  public void setClientIP(String clientIP) {
    this.ClientIP = clientIP;
  }

  public HashMap<String, String> getOtherdata() {
    return Otherdata;
  }

  public void setOtherdata(HashMap<String, String> otherdata) {
    this.Otherdata = otherdata;
  }

}