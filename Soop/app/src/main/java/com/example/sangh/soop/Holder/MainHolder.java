package com.example.sangh.soop.Holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sangh.soop.ContentActivity;
import com.example.sangh.soop.Model.MainItem;
import com.example.sangh.soop.R;
import com.example.sangh.soop.view.GreenToast;

/**
 * Created by koohanmo on 2017-02-19.
 */

public class MainHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private MainItem mItem;
    private ImageView mUniMark;
    private TextView mDate;
    private TextView mUniName;
    private TextView mLike;
    private TextView mComment;
    private TextView mBody;

    private Context mcon;

    public MainHolder(View itemView, Context mcon){
        super(itemView);
        this.mcon = mcon;
        itemView.setOnClickListener(this);
        mUniMark = (ImageView) itemView.findViewById(R.id.uni_mark);
        mDate =(TextView) itemView.findViewById(R.id.main_date);
        mUniName = (TextView) itemView.findViewById(R.id.uni_name);
        mLike = (TextView) itemView.findViewById(R.id.like);
        mComment = (TextView) itemView.findViewById(R.id.comment);
        mBody = (TextView) itemView.findViewById(R.id.body);

    }

    public void onBindView(MainItem item){
        mItem = item;
        mUniMark.setImageResource(mItem.getUniMark());
        mUniName.setText(mItem.getUniName());
        mLike.setText(mItem.getLike()+"");
        mComment.setText(mItem.getComment()+"");
        mBody.setText(mItem.getBody());
        mDate.setText(mItem.getDate());
    }

    @Override
    public void onClick(View view){
        new GreenToast(mcon).showToast("선택됨!");
        Intent intent = new Intent(mcon, ContentActivity.class);
        intent.putExtra("uniMark",mItem.getUniMark());
        intent.putExtra("date",mItem.getDate());
        intent.putExtra("uniName",mItem.getUniName());
        intent.putExtra("like",mItem.getLike());
        intent.putExtra("comment",mItem.getComment());
        intent.putExtra("body",mItem.getBody());
        mcon.startActivity(intent);
    }
}