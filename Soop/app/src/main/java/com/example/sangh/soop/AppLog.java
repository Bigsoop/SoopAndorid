package com.example.sangh.soop;

import android.util.Log;

/**
 * Created by koohanmo on 2017-02-15.
 */

public class AppLog {

    public static boolean DEBUG = true;

    public static final void e(String TAG, String msg){
        if(DEBUG) Log.e(TAG,msg);
    }
    public static final void w(String TAG, String msg){
        if(DEBUG) Log.w(TAG,msg);
    }
    public static final void i(String TAG, String msg){
        if(DEBUG) Log.i(TAG,msg);
    }
    public static final void d(String TAG, String msg){
        if(DEBUG) Log.d(TAG,msg);
    }
    public static final void v(String TAG, String msg){
        if(DEBUG) Log.v(TAG,msg);
    }

}
