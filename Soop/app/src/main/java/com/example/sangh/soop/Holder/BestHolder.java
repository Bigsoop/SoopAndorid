package com.example.sangh.soop.Holder;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.example.sangh.soop.R;

/**
 * Created by koohanmo on 2017-02-19.
 */

public class BestHolder extends MainHolder {

    private TextView txt_best_idx;

    public BestHolder(View itemView, Context mcon) {
        super(itemView, mcon);
        txt_best_idx = (TextView) itemView.findViewById(R.id.txt_best_idx);
    }

    public void setRank(int idx){
        String rank = " "+idx;
        if(idx < 9) rank = " "+rank;
        txt_best_idx.setText("BEST"+rank);
    }
    public void setTypeFace(Typeface ty){
        txt_best_idx.setTypeface(ty);
    }
}
