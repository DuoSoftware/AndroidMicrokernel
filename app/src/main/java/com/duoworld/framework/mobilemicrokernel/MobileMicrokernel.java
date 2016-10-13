package com.duoworld.framework.mobilemicrokernel;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.duoworld.framework.mobilemicrokernel.core.auth.*;
import com.duoworld.framework.mobilemicrokernel.core.ceb.CebClient;
import com.duoworld.framework.mobilemicrokernel.core.ceb.CebListenerConnected;
import com.duoworld.framework.mobilemicrokernel.core.ceb.CebResponseConnect;

public final class MobileMicrokernel{

    private SharedPreferences appSettings;
    private Context appContext;

    private AuthResponseLogin authData;
    private AuthClient authClient;
    private CebClient cebClient;

    private MicrokernelListenerAttached mkAttachedListener;

    public boolean IsAuthenticated(){
        boolean isLoggedIn = false;

        if (authData != null)
            if (!authData.isError())
                isLoggedIn = true;

        return isLoggedIn;
    }

    public void Authenticate(String username, String password, String domainName, final AuthListenerLogin listener){
        Authenticate(new AuthRequestLogin(username, password, domainName), listener);
    }

    public void Authenticate (AuthRequestLogin request, final AuthListenerLogin listener){
        this.authClient.Login(request, new AuthListenerLogin() {
            @Override
            public void OnLogin(AuthResponseLogin response) {
                if (!response.isError())
                    authData = response;

                if (listener !=null)
                    listener.OnLogin(response);
            }
        });
    }

    public void Attach(MicrokernelListenerAttached listener) throws NotAuthenticatedException {
        this.mkAttachedListener = listener;
        if (!IsAuthenticated())
            throw new NotAuthenticatedException();

        this.cebClient.setOnConnectListener(onCebConnected);
        this.cebClient.Register(this.authData);
    }

    private CebListenerConnected onCebConnected = new CebListenerConnected() {
        @Override
        public void OnRegistered(CebResponseConnect response) {
            mkAttachedListener.OnAttached(new MicrokernelResponseAttached(response));
        }
    };

    public void Logout(){

    }

    public AuthClient getAuthClient(){
        return this.authClient;
    }

    public MobileMicrokernel(Context appContext){
        this.appContext = appContext;
        this.appSettings = PreferenceManager.getDefaultSharedPreferences(appContext);

        this.authClient = new AuthClient();
        this.cebClient = new CebClient();
    }
}