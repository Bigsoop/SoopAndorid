package com.example.sangh.soop;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.sangh.soop.view.GreenToast;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import java.util.Arrays;
import java.util.concurrent.CompletionService;

public class IntroLoginActivity extends AppCompatActivity {
    private final String TAG ="IntroLoginActivity";
    LoginButton mLoginButton;
    CallbackManager callbackManager;
    boolean loginStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginStatus = User.getIsLogin(this);
        if(loginStatus){
            setContentView(R.layout.activity_intro);
            Handler hd = new Handler();
            hd.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(IntroLoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }, 1500);
        }

        else {
            setContentView(R.layout.activity_intro_login);
            callbackManager = CallbackManager.Factory.create();
            mLoginButton = (LoginButton) findViewById(R.id.login_button);
            mLoginButton.setReadPermissions(Arrays.asList("public_profile,manage_pages,publish_pages,publish_actions,user_events"));
            mLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

                @Override
                public void onSuccess(LoginResult loginResult) {
                    AppLog.i(TAG,loginResult+"");
                    Intent intent = new Intent(IntroLoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    User.setIsLogin(getApplicationContext(),true);
                    startActivity(intent);
                    finish();
                }
                @Override
                public void onCancel() {
                    new GreenToast(getApplicationContext()).showToast("Cancel");
                }

                @Override
                public void onError(FacebookException error) {

                }
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
