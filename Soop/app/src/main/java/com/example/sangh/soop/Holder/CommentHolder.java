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
import com.example.sangh.soop.R;
import com.example.sangh.soop.CommentActivity;
import com.example.sangh.soop.Common;
import com.example.sangh.soop.Model.CommentItem;


/**
 * Created by sangh on 2017-02-16.
 */

public class CommentHolder extends BaseViewHolder<CommentItem>{
    private Context mCon;
    private CommentItem mItem;
    private ImageView mUserImg;
    private TextView mUserName;
    private TextView mDate;
    private TextView mLike;
    private TextView mComment;
    private TextView mBody;
    private LinearLayout commentBtn;
    private LinearLayout likeBtn;

    public static CommentHolder newInstance(Context con,ViewGroup parent){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        return new CommentHolder(con,itemView);
    }

    public static CommentHolder newInstance2(Context con,ViewGroup parent){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item_color, parent, false);
        return new CommentHolder(con,itemView);
    }


    public CommentHolder(Context con,View itemView) {
        super(itemView);
        this.mCon = con;
        mUserImg = (ImageView) itemView.findViewById(R.id.userImg_comment);
        mUserName = (TextView) itemView.findViewById(R.id.userName_comment);
        mDate =(TextView) itemView.findViewById(R.id.date_comment);
        mLike = (TextView) itemView.findViewById(R.id.like_comment);
        mComment = (TextView) itemView.findViewById(R.id.comment_comment);
        mBody = (TextView) itemView.findViewById(R.id.body_comment);
        commentBtn = (LinearLayout) itemView.findViewById(R.id.comment_linear);
        likeBtn =(LinearLayout)itemView.findViewById(R.id.like_linear);
    }


    public void onBindView(CommentItem item) {
        mItem = item;
        mUserName.setText(mItem.getUserName());
        mLike.setText(" 좋아요 " + mItem.getLike() + "명");
        mComment.setText(" 댓글 " + mItem.getComment() + "개");
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

        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItem.isComment_able()) {
                    Intent intent = new Intent(mCon, CommentActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("commentId", mItem.getId());
                    intent.putExtra("commentLike", mItem.getLike());
                    intent.putExtra("commentComment", mItem.getComment());
                    intent.putExtra("commentCreateTime", mItem.getDate());
                    intent.putExtra("commentUserImg", mItem.getUserImg());
                    intent.putExtra("commentBody", mItem.getBody());
                    intent.putExtra("commentUserId", mItem.getUserId());
                    intent.putExtra("commentUserName", mItem.getUserName());
                    mCon.startActivity(intent);
                }
            }
        });

        likeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"+ mItem.getId()));
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    mCon.startActivity(i);
            }
        });

    }

}
