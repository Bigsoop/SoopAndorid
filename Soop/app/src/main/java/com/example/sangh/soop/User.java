package com.example.sangh.soop;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by koohanmo on 2017-02-19.
 */

public class User {

    private static SharedPreferences preferences;

    public static void setIsLogin(Context context, boolean is){
        preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLogin",is);
        editor.commit();
    }

    public static boolean getIsLogin(Context context){
        preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        return preferences.getBoolean("isLogin",false);
    }


}
