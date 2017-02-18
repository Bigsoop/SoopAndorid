package com.example.sangh.soop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sangh.soop.view.GreenToast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class IntroLoginActivity extends AppCompatActivity {
    Button startBtn;
    LoginButton mLoginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_intro_login);
        init();
        loginWithFB();

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroLoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        callbackManager =CallbackManager.Factory.create();
        startBtn= (Button)(findViewById(R.id.btn_start));
        mLoginButton  =(LoginButton) findViewById(R.id.login_button);
    }

    private void loginWithFB(){
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        new GreenToast(getApplicationContext()).showToast("LoginSuccess\n"+loginResult.getAccessToken());
                        Intent intent = new Intent(IntroLoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {
                        new GreenToast(getApplicationContext()).showToast("LoginCancelled");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        new GreenToast(getApplicationContext()).showToast("LoginError "+error.getMessage());
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
