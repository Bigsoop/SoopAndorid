package com.example.sangh.soop.Holder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sangh.soop.Model.ContentItem;
import com.example.sangh.soop.R;

/**
 * Created by sangh on 2017-02-16.
 */

public class ContentHolder extends BaseViewHolder<ContentItem>{

        private ContentItem mItem;
        private ImageView mUniMark;
        private TextView mDate;
        private TextView mUniName;
        private TextView mLike;
        private TextView mComment;
        private TextView mBody;
        private TextView mShare;

        public static ContentHolder newInstance(ViewGroup parent){
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.content_item, parent, false);
            return new ContentHolder(itemView);
        }

        public ContentHolder(View itemView){
            super(itemView);
            mUniMark = (ImageView) itemView.findViewById(R.id.uni_mark_content);
            mDate =(TextView) itemView.findViewById(R.id.main_date_content);
            mUniName = (TextView) itemView.findViewById(R.id.uni_name_content);
            mLike = (TextView) itemView.findViewById(R.id.like_content);
            mComment = (TextView) itemView.findViewById(R.id.comment_content);
            mBody = (TextView) itemView.findViewById(R.id.body_content);
            mShare =(TextView)itemView.findViewById(R.id.share_content);
        }

        public void onBindView(ContentItem item){
            mItem = item;
            mUniMark.setImageResource(mItem.getUniMark());
            mUniName.setText(mItem.getUniName());
            mLike.setText("좋아요 "+mItem.getLike()+"명");
            mComment.setText("댓글 "+mItem.getComment()+"개");
            mShare.setText("공유" +mItem.getShare()+"회");
            mBody.setText(mItem.getBody());
            mDate.setText(mItem.getDate());
        }
}
