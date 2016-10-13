package com.duoworld.framework.mobilemicrokernel.core.auth;

import com.duoworld.framework.mobilemicrokernel.MicrokernelUrls;
import com.duoworld.framework.mobilemicrokernel.helpers.*;

public class AuthClient {

    private RestInvoker invoker;

    public void Login(AuthRequestLogin request, final AuthListenerLogin listener){
        String loginUrl =  MKStringHelper.AppendByDelimeter("/", MicrokernelUrls.Auth , "Login" , request.getUserName(), request.getPassword(), request.getDomainName());

        invoker.Get(loginUrl, null, new RestListener() {
            @Override
            public void OnResponse(RestResponse response){
                AuthResponseLogin r = null;

                if (!response.isError())
                    r = response.asJSON(AuthResponseLogin.class);


                if (r==null){
                    r = new AuthResponseLogin();
                    r.setError(true);
                    r.setErrorMessage(response.getResponse());
                }

                if (listener!=null)
                    listener.OnLogin(r);
            }
        });
    }

    public AuthClient(){
        this.invoker = new RestInvoker();
    }
}