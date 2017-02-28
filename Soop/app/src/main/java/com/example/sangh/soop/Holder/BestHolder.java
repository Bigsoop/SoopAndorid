package com.example.sangh.soop.Holder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
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
    private LinearLayout likeBtn;
    private Context mCon;
    private LinearLayout shareBtn;

    public BestHolder(View itemView, Context mcon) {
        super(itemView, mcon);
        this.mCon=mcon;
        txt_best_idx = (TextView) itemView.findViewById(R.id.txt_best_idx);
        share = (TextView) itemView.findViewById(R.id.share);
        shareBtn =(LinearLayout)itemView.findViewById(R.id.share_linear);
        likeBtn =(LinearLayout)itemView.findViewById(R.id.share_linear);
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

        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =Common.getFacebookIntent(mCon, Uri.parse("https://www.facebook.com/"+mItem.getId()+"/"));
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                mCon.startActivity(i);
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i =Common.getFacebookIntent(mCon, Uri.parse("https://www.facebook.com/"+mItem.getId()+"/"));
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                mCon.startActivity(i);
            }
        });


    }

}
