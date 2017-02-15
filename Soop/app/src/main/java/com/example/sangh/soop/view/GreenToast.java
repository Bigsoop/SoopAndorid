package com.example.sangh.soop.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sangh.soop.R;

/**
 * Created by koohanmo on 2017-02-15.
 */

public class GreenToast extends Toast {

    TextView txtMsg;

    public GreenToast(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.toast_green, null);
        txtMsg = (TextView)layout.findViewById(R.id.toast_txt);
        this.setDuration(this.LENGTH_SHORT);
        this.setView(layout);
    }

    public void showToast(String msg){
        txtMsg.setText(msg);
        this.show();
    }
}
