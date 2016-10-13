package framework.duoworld.com.mobilemicrokernel;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.duoworld.framework.mobilemicrokernel.MicrokernelListenerAttached;
import com.duoworld.framework.mobilemicrokernel.MicrokernelResponseAttached;
import com.duoworld.framework.mobilemicrokernel.MobileMicrokernel;
import com.duoworld.framework.mobilemicrokernel.core.auth.AuthListenerLogin;
import com.duoworld.framework.mobilemicrokernel.core.auth.AuthResponseLogin;
import com.duoworld.framework.mobilemicrokernel.core.auth.NotAuthenticatedException;


public class MainActivity extends AppCompatActivity {

    private class Controls {
        public Button button1;
    }
    private Controls c;

    private MobileMicrokernel microkernel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.microkernel = new MobileMicrokernel(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadResources();
    }

    private void loadResources(){
        c = new Controls();
        c.button1 = (Button)findViewById(R.id.buttonTest);
        c.button1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }


    private void login (){
        if (!microkernel.IsAuthenticated()){
            microkernel.Authenticate("supun@duosoftware.com", "admin", "developer.duoworld.com", new AuthListenerLogin() {
                @Override
                public void OnLogin(AuthResponseLogin response) {
                    if (!response.isError()) {
                        attachKernel();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Error Loggin In :(", Toast.LENGTH_SHORT);
                    }
                }
            });
        }else{
            attachKernel();
        }
    }

    private void attachKernel(){
        try {
            microkernel.Attach(new MicrokernelListenerAttached() {
                @Override
                public void OnAttached(MicrokernelResponseAttached response) {

                }
            });
        } catch (NotAuthenticatedException e) {
            e.printStackTrace();
        }
    }

    private void test(){

    }
}
