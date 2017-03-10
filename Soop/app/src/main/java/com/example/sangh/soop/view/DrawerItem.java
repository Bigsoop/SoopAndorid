package com.example.sangh.soop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sangh.soop.R;

/**
 * Created by sangh on 2017-02-10.
 */

public class DrawerItem extends LinearLayout {

    private Context mCon;
    private ImageView img_icon;
    private TextView txt_menu;

    public DrawerItem(Context context, int draw, String txt) {
        super(context);
        this.mCon = context;
        initView();
        setItemView(draw,txt);
    }


    public DrawerItem(Context context) {
        super(context);
        this.mCon = context;
        initView();
    }

    public DrawerItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCon = context;
        initView();
    }

    private void initView(){
        LayoutInflater.from(mCon).inflate(R.layout.drawer_item,this);
        txt_menu = (TextView)findViewById(R.id.nav_text);
        this.setClickable(true);

    }

    public void setItemView(int draw, String txt){
        txt_menu.setText(txt);
    }

}