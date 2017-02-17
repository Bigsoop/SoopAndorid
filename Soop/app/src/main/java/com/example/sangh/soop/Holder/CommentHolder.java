package com.example.sangh.soop.Holder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sangh.soop.Model.CommentItem;
import com.example.sangh.soop.R;

/**
 * Created by sangh on 2017-02-16.
 */

public class CommentHolder extends BaseViewHolder<CommentItem>{

    private CommentItem mItem;
    private ImageView mUserImg;
    private TextView mUserName;
    private TextView mDate;
    private TextView mLike;
    private TextView mComment;
    private TextView mBody;

    public static CommentHolder newInstance(ViewGroup parent){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        return new CommentHolder(itemView);
    }

    public CommentHolder(View itemView){
        super(itemView);
        mUserImg = (ImageView) itemView.findViewById(R.id.userImg_comment);
        mUserName = (TextView) itemView.findViewById(R.id.userName_comment);
        mDate =(TextView) itemView.findViewById(R.id.date_comment);
        mLike = (TextView) itemView.findViewById(R.id.like_comment);
        mComment = (TextView) itemView.findViewById(R.id.comment_comment);
        mBody = (TextView) itemView.findViewById(R.id.body_comment);
    }

    public void onBindView(CommentItem item){
        mItem = item;
        mUserImg.setImageResource(mItem.getUserImg());
        mUserName.setText(mItem.getUserName());
        mLike.setText(mItem.getLike()+"");
        mComment.setText(mItem.getComment()+"");
        mBody.setText(mItem.getBody());
        mDate.setText(mItem.getDate());
    }
}
