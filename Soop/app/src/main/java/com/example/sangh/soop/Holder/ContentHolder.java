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
import com.example.sangh.soop.AppLog;
import com.example.sangh.soop.Common;
import com.example.sangh.soop.Model.ContentItem;
import com.example.sangh.soop.R;
import com.example.sangh.soop.view.GreenToast;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sangh on 2017-02-16.
 */

public class ContentHolder extends BaseViewHolder<ContentItem>{
        private final String TAG="ContentHolder";
        private Context mContext;
        private ContentItem mItem;
        private ImageView mUniMark;
        private TextView mDate;
        private TextView mUniName;
        private TextView mLike;
        private TextView mComment;
        private TextView mBody;
        private TextView mShare;
        private LinearLayout layLike;
        private LinearLayout shareBtn;

        public static ContentHolder newInstance(Context con,ViewGroup parent){
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.content_item, parent, false);
            return new ContentHolder(con, itemView);
        }

        public ContentHolder(Context con,View itemView){
            super(itemView);
            this.mContext=con;
            mUniMark = (ImageView) itemView.findViewById(R.id.uni_mark_content);
            mDate =(TextView) itemView.findViewById(R.id.main_date_content);
            mUniName = (TextView) itemView.findViewById(R.id.uni_name_content);
            mLike = (TextView) itemView.findViewById(R.id.like_content);
            mComment = (TextView) itemView.findViewById(R.id.comment_content);
            mBody = (TextView) itemView.findViewById(R.id.body_content);
            mShare =(TextView)itemView.findViewById(R.id.share_content);
            layLike= (LinearLayout)itemView.findViewById(R.id.like_linear);
            shareBtn= (LinearLayout)itemView.findViewById(R.id.share_linear);
        }

        public void onBindView(ContentItem item){
            mItem = item;
            mUniName.setText(mItem.getUniName()+" 대나무숲");
            mLike.setText(" 좋아요 "+mItem.getLike()+"명");
            mComment.setText(" 댓글 "+mItem.getComment()+"개");
            mShare.setText(" 공유 " +mItem.getShare()+"회");
            mBody.setText(mItem.getBody());
            mDate.setText(mItem.getDate());
            Common.setCircleImage(mContext, mItem.getUniMark(), mUniMark);
            layLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i =Common.getFacebookIntent(mContext , Uri.parse("https://www.facebook.com/"+mItem.getId()+"/"));
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);
                }
            });

            shareBtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent i =Common.getFacebookIntent(mContext, Uri.parse("https://www.facebook.com/"+mItem.getId()+"/"));
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);
                }
            });
        }



}
