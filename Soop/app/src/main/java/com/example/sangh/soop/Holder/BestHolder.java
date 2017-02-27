package com.example.sangh.soop.Holder;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.example.sangh.soop.Common;
import com.example.sangh.soop.Model.MainItem;
import com.example.sangh.soop.R;

/**
 * Created by koohanmo on 2017-02-19.
 */

public class BestHolder extends MainHolder {

    private TextView txt_best_idx;
    private TextView share;

    public BestHolder(View itemView, Context mcon) {
        super(itemView, mcon);
        txt_best_idx = (TextView) itemView.findViewById(R.id.txt_best_idx);
        share = (TextView) itemView.findViewById(R.id.share);
    }

    public void setRank(int idx){
        String rank = " "+idx;
        if(idx < 9) rank = " "+rank;
        txt_best_idx.setText("BEST"+rank);
    }
    public void setTypeFace(Typeface ty){
        txt_best_idx.setTypeface(ty);
    }

    @Override
    public void onBindView(MainItem item){
        mItem = item;
        mUniName.setText(mItem.getUniName()+" 대나무숲");
        mLike.setText(" 좋아요 "+mItem.getLike()+"개");
        mComment.setText(" 댓글 "+mItem.getComment()+"개");
        share.setText(" 공유 "+mItem.getShare()+"회");
        mBody.setText(mItem.getBody());
        mDate.setText(mItem.getDate());
        Common.setCircleImage(mcon, mItem.getUniMark(), mUniMark);
    }

}
