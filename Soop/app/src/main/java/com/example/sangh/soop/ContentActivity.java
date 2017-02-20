package com.example.sangh.soop;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.sangh.soop.Holder.BaseViewHolder;
import com.example.sangh.soop.Holder.CommentHolder;
import com.example.sangh.soop.Holder.ContentHolder;
import com.example.sangh.soop.Model.CommentItem;
import com.example.sangh.soop.Model.ContentItem;
import com.example.sangh.soop.Model.MainItem;
import com.example.sangh.soop.view.GreenToast;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sangh on 2017-02-15.
 */

public class ContentActivity extends AppCompatActivity {

    private final String TAG ="ContentActivity";
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ContentAdapter mAdapter;
    private List<MainItem> mMultipleItems;
    private Context mContext;
    int uniMark;
    int comment;
    int like;
    int share;
    String id;
    String date;
    String uniName;
    String body;
    Button inputCommentBtn;
    EditText editComment;

    @Override
    protected void onCreate(Bundle savedSavedInstance) {
        super.onCreate(savedSavedInstance);
        setContentView(R.layout.activity_content);
        mContext=getApplicationContext();
        mToolbar = (Toolbar) findViewById(R.id.toolbar_content);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.content_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        uniMark = intent.getExtras().getInt("uniMark");
        comment = intent.getExtras().getInt("comment");
        like = intent.getExtras().getInt("like");
        date = intent.getExtras().getString("date");
        uniName = intent.getExtras().getString("uniName");
        body = intent.getExtras().getString("body");
        share = intent.getExtras().getInt("share");


        inputData();
        updateUI();


        editComment =(EditText)findViewById(R.id.editText);
        inputCommentBtn = (Button) findViewById(R.id.input_comment_btn);
        inputCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editComment.getText().toString();
                if(text.length()==0) return;
                Bundle params = new Bundle();
                params.putString("message", text);
                new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/"+ id + "/comments",
                        params,
                        HttpMethod.POST,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                new GreenToast(getApplicationContext()).showToast("댓글이 등록되었습니다.");
                                //AppLog.i(TAG,  "/"+ id + "/comments"
                            }
                        }
                ).executeAsync();

                editComment.setText("");
            }
        });
    }

    private void updateUI() {
        mAdapter = new ContentAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void inputData() {
        mMultipleItems = new ArrayList();
        final ContentItem contentItem = new ContentItem();
        contentItem.setId(id);
        contentItem.setUniMark(uniMark);
        contentItem.setLike(like);
        contentItem.setDate(date);
        contentItem.setComment(comment);
        contentItem.setUniName(uniName);
        contentItem.setShare(share);
        contentItem.setBody(body);
        mMultipleItems.add(contentItem);

            new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/" + id + "/comments",
                    null,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
                            try {
                                JSONObject jo= response.getJSONObject();
                                AppLog.i(TAG,jo.toString());
                                JSONArray ja = jo.getJSONArray("data");

                                for(int i=0; i<ja.length(); i++){
                                    final CommentItem commentItem = new CommentItem();
                                    JSONObject cur = ja.getJSONObject(i);
                                    JSONObject from = cur.getJSONObject("from");
                                    commentItem.setUserId(from.getString("id"));
                                    commentItem.setUserName(from.getString("name"));
                                    commentItem.setId(cur.getString("id"));
                                    commentItem.setDate(cur.getString("created_time"));
                                    commentItem.setBody(cur.getString("message"));

                                    new GraphRequest(
                                            AccessToken.getCurrentAccessToken(),
                                            "/" + commentItem.getId()+ "?fields=like_count,comment_count",
                                            null,
                                            HttpMethod.GET,
                                            new GraphRequest.Callback() {
                                                public void onCompleted(GraphResponse response) {
                                                    try{
                                                        JSONObject like_comment_count = response.getJSONObject();
                                                        commentItem.setLike(Integer.parseInt(like_comment_count.getString("like_count")));
                                                        commentItem.setComment(Integer.parseInt(like_comment_count.getString("comment_count")));
                                                    }catch (JSONException e){
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                    ).executeAsync();

                                    new GraphRequest(
                                            AccessToken.getCurrentAccessToken(),
                                            "/" + commentItem.getUserId()+"/picture?redirect=false",
                                            null,
                                            HttpMethod.GET,
                                            new GraphRequest.Callback() {
                                                public void onCompleted(GraphResponse response) {
                                                    try{
                                                        AppLog.i(TAG,"/" + commentItem.getUserId()+"/picture");
                                                        JSONObject userImg = response.getJSONObject();
                                                        JSONObject data = userImg.getJSONObject("data");
                                                        commentItem.setUserImg(data.getString("url"));
                                                    }catch (JSONException e){
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                    ).executeAsync();

                                    mMultipleItems.add(commentItem);
                                }
                                mAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            ).executeAsync();
    }



    public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public static final int VIEW_TYPE_CONTENT = 0;
        public static final int VIEW_TYPE_COMMENT = 1;

        public ContentAdapter() {
        }

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_CONTENT) {
                return ContentHolder.newInstance(mContext,parent);
            } else {
                return CommentHolder.newInstance(mContext, parent);
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
