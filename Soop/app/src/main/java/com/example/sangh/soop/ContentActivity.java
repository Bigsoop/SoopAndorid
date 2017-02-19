package com.example.sangh.soop;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import com.example.sangh.soop.Holder.BaseViewHolder;
import com.example.sangh.soop.Holder.CommentHolder;
import com.example.sangh.soop.Holder.ContentHolder;
import com.example.sangh.soop.Model.CommentItem;
import com.example.sangh.soop.Model.ContentItem;
import com.example.sangh.soop.Model.MainItem;;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sangh on 2017-02-15.
 */

public class ContentActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ContentAdapter mAdapter;
    private List<MainItem> mMultipleItems;
    int uniMark;
    int comment;
    int like;
    int share;
    String date;
    String uniName;
    String body;

    @Override
    protected void onCreate(Bundle savedSavedInstance) {
        super.onCreate(savedSavedInstance);
        setContentView(R.layout.activity_content);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_content);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.content_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Intent intent = getIntent();
        uniMark = intent.getExtras().getInt("uniMark");
        comment = intent.getExtras().getInt("comment");
        like = intent.getExtras().getInt("like");
        date = intent.getExtras().getString("date");
        uniName = intent.getExtras().getString("uniName");
        body = intent.getExtras().getString("body");
        share = intent.getExtras().getInt("share");

        DummyData();
        updateUI();
    }

    private void updateUI() {
        mAdapter = new ContentAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void DummyData() {
        mMultipleItems = new ArrayList();
        ContentItem contentItem = new ContentItem();
        contentItem.setUniMark(uniMark);
        contentItem.setLike(like);
        contentItem.setDate(date);
        contentItem.setComment(comment);
        contentItem.setUniName(uniName);
        contentItem.setShare(share);
        contentItem.setBody(body);
        mMultipleItems.add(contentItem);

        for (int i = 0; i < 5; i++) {
            CommentItem commentItem = new CommentItem();
            commentItem.setUserImg(R.drawable.sulhyun);
            commentItem.setUserName("김설현");
            commentItem.setBody("스탠포드 가구씨보용 ~ ㅋㄷㅋㄷㅋㄷㅋㄷㅋㄷㅋㄷㅋㄷㅋㄷ 한국엔 항공대" +
                    "미국엔 스탠포드!");
            commentItem.setLike(i + 100);
            commentItem.setComment(i + 110);
            commentItem.setDate("2017년 2월 18일 오전 12:28");
            mMultipleItems.add(commentItem);
        }
    }

    public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public static final int VIEW_TYPE_CONTENT = 0;
        public static final int VIEW_TYPE_COMMENT = 1;

        public ContentAdapter() {

        }

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_CONTENT) {
                return ContentHolder.newInstance(parent);
            } else {
                return CommentHolder.newInstance(parent);
            }
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            if (holder.getItemViewType()==VIEW_TYPE_CONTENT) {
                ContentItem contentItem = (ContentItem) mMultipleItems.get(position);
                if(holder instanceof ContentHolder){
                    ((ContentHolder) holder).onBindView(contentItem);
                }
            } else {
                CommentItem commentItem =(CommentItem)mMultipleItems.get(position);
                if(holder instanceof CommentHolder) {
                    ((CommentHolder) holder).onBindView(commentItem);
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) return VIEW_TYPE_CONTENT;
            else return VIEW_TYPE_COMMENT;
        }

        @Override
        public int getItemCount() {
          return  mMultipleItems.size();
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
