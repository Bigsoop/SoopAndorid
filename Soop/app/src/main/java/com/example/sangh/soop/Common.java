package com.example.sangh.soop;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ParseException;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.Profile;

import java.text.SimpleDateFormat;
import java.util.Date;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by koohanmo on 2017-02-19.
 */

public class Common {


    public static Intent getFacebookIntent(Context context, Uri uri) {

        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                uri = Uri.parse("fb://facewebmodal/f?href=" + uri.toString());
            }
        }
        catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    public static void setCircleImage(Context context, String url, ImageView img){
        try {
            Glide.with(context).load(url)
                    .bitmapTransform(new CropCircleTransformation(Glide.get(context).getBitmapPool()))
                    .placeholder(R.drawable.profile_image)
                    .into(img);
        }catch (Exception e){
            AppLog.e("CircleImage", "Context Error");
        }
    }
    public static void setCircleImage(Context context, Uri uri, ImageView img){
        try {
            Glide.with(context).load(uri)
                    .bitmapTransform(new CropCircleTransformation(Glide.get(context).getBitmapPool()))
                    .into(img);
        }catch (Exception e){
            AppLog.e("CircleImage", "Context Error");

        }
    }

    public static String dateFormat(String date)
    {
        String d = (String) date.subSequence(0,10);
        String t = (String) date.subSequence(11,19);
        return d+" "+t;
    }
}
