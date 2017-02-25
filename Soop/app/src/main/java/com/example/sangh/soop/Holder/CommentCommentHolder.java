package com.example.sangh.soop.Holder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sangh.soop.Common;
import com.example.sangh.soop.Model.CommentItem;
import com.example.sangh.soop.R;

/**
 * Created by sangh on 2017-02-24.
 */


public class CommentCommentHolder extends BaseViewHolder<CommentItem>{
    private Context mCon;
    private CommentItem mItem;
    private ImageView mUserImg;
    private TextView mUserName;
    private TextView mDate;
    private TextView mLike;
    private TextView mBody;
    private LinearLayout likeBtn;

    public static CommentCommentHolder newInstance(Context con,ViewGroup parent){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_comment_item, parent, false);
        return new CommentCommentHolder(con,itemView);
    }


    public CommentCommentHolder(Context con,View itemView) {
        super(itemView);
        this.mCon = con;
        mUserImg = (ImageView) itemView.findViewById(R.id.userImg_no_comment);
        mUserName = (TextView) itemView.findViewById(R.id.userName_no_comment);
        mDate =(TextView) itemView.findViewById(R.id.date_no_comment);
        mLike = (TextView) itemView.findViewById(R.id.like_no_comment);
        mBody = (TextView) itemView.findViewById(R.id.body_no_comment);
        likeBtn =(LinearLayout)itemView.findViewById(R.id.like_no_linear);
    }


    public void onBindView(CommentItem item) {
        mItem = item;
        mUserName.setText(mItem.getUserName());
        mLike.setText(" 좋아요 " + mItem.getLike() + "명");
        mBody.setText(mItem.getBody());
        mDate.setText(mItem.getDate());
        Common.setCircleImage(mCon, mItem.getUserImg(), mUserImg);

        mUserImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =Common.getFacebookIntent(mCon , Uri.parse("https://www.facebook.com/app_scoped_user_id/"+mItem.getUserId()+"/"));
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                mCon.startActivity(i);
            }
        });


        likeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mLike.setText(" 좋아요 "+ (mItem.getLike()+1) +"명");
            }
        });

    }

}
