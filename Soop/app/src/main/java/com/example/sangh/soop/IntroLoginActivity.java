package com.example.sangh.soop;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.sangh.soop.view.GreenToast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class IntroLoginActivity extends AppCompatActivity {
    private final String TAG ="IntroLoginActivity";

    Button startBtn;
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
                    startActivity(new Intent(IntroLoginActivity.this, MainActivity.class));
                }
            }, 1500);
        }

        else {
            setContentView(R.layout.activity_intro_login);
            callbackManager = CallbackManager.Factory.create();
            mLoginButton = (LoginButton) findViewById(R.id.login_button);
            mLoginButton.setReadPermissions("public_profile");
            mLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    AppLog.i(TAG,loginResult+"");
                    String accessToken = loginResult.getAccessToken().toString();
                    new GreenToast(getApplicationContext()).showToast("Success " + accessToken);
                    Intent intent = new Intent(IntroLoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
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

            startBtn = (Button) (findViewById(R.id.btn_start));
            startBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(IntroLoginActivity.this, MainActivity.class);
                    startActivity(intent);
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
