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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sangh on 2017-02-15.
 */

public class ContentActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ContentAdapter mAdapter;
    private List<CommentItem> mCommentItems;
    int uniMark;
    int comment;
    int like;
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

        DummyData();
        updateUI();
    }

    private void updateUI() {

        mAdapter = new ContentAdapter(mCommentItems);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void DummyData() {
        mCommentItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CommentItem commentItem = new CommentItem();
            commentItem.setUserImg(R.drawable.sulhyun);
            commentItem.setUserName("김설현");
            commentItem.setBody("스탠포드 가구씨보용 ~ ㅋㄷㅋㄷㅋㄷㅋㄷㅋㄷㅋㄷㅋㄷㅋㄷ 한국엔 항공대" +
                    "미국엔 스탠포드!");
            commentItem.setLike(i + 100);
            commentItem.setComment(i + 110);
            commentItem.setDate("2017년 2월 18일 오전 12:28");
            mCommentItems.add(commentItem);
        }
    }

    public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public static final int VIEW_TYPE_CONTENT = 0;
        public static final int VIEW_TYPE_COMMENT = 1;

        public ContentAdapter(List<CommentItem> commentItems) {}

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_CONTENT) {
                return CommentHolder.newInstance(parent);
            } else {
                return ContentHolder.newInstance(parent);
            }
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            switch (holder.getItemViewType()) {
                case VIEW_TYPE_CONTENT:
                    ContentHolder ch = (ContentHolder) holder;
                    ContentItem contentItem = new ContentItem();
                    contentItem.setLike(like);
                    contentItem.setDate(date);
                    contentItem.setComment(comment);
                    contentItem.setUniName(uniName);
                    contentItem.setShare(20);
                    contentItem.setBody(body);
                    ch.onBindView(contentItem);
                    break;

                case VIEW_TYPE_COMMENT:
                    CommentHolder cmh = (CommentHolder) holder;
                    CommentItem commentItem = mCommentItems.get(position);
                    cmh.onBindView(commentItem);
                    break;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) return VIEW_TYPE_CONTENT;
            else return VIEW_TYPE_COMMENT;
        }

        @Override
        public int getItemCount() {
            return 0;
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
