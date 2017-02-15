package com.example.sangh.soop;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sangh.soop.Model.MainItem;

/**
 * Created by sangh on 2017-02-15.
 */

public class ContentActivity extends AppCompatActivity{
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedSavedInstance){
        super.onCreate(savedSavedInstance);
        setContentView(R.layout.activity_content);

        mToolbar=(Toolbar)findViewById(R.id.toolbar_content);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView =(RecyclerView) findViewById(R.id.content_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    private class ContentHolder extends RecyclerView.ViewHolder {
        private MainItem mItem;
        private ImageView mUniMark;
        private TextView mDate;
        private TextView mUniName;
        private TextView mLike;
        private TextView mComment;
        private TextView mBody;

        public ContentHolder(View itemView){
            super(itemView);
            //mUniMark = (ImageView) itemView.findViewById(R.id.uni_mark);
            mDate =(TextView) itemView.findViewById(R.id.main_date);
            mUniName = (TextView) itemView.findViewById(R.id.uni_name);
            mLike = (TextView) itemView.findViewById(R.id.like);
            mComment = (TextView) itemView.findViewById(R.id.comment);
            mBody = (TextView) itemView.findViewById(R.id.body);
        }

        public void bindContentItem(MainItem item){
            mItem = item;
            //mUniMark.setImageResource(mItem.getUniMark());
            mUniName.setText(mItem.getUniName());
            mLike.setText(mItem.getLike()+"");
            mComment.setText(mItem.getComment()+"");
            mBody.setText(mItem.getBody());
            mDate.setText(mItem.getDate());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
