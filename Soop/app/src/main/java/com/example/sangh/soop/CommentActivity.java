

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
import com.example.sangh.soop.Holder.CommentCommentHolder;
import com.example.sangh.soop.Holder.CommentHolder;
import com.example.sangh.soop.Model.CommentItem;
import com.example.sangh.soop.Model.MainItem;
import com.example.sangh.soop.view.GreenToast;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sangh on 2017-02-20.
 */

public class CommentActivity extends AppCompatActivity {

    private final String TAG = "CommentActivity";
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private List<MainItem> mMultipleItems;
    private CommentAdapter mCommentAdapter;
    private Context mContext;
    Button inputCommentBtn;
    EditText editComment;
    private String commentId;
    private String createTime;
    private String userImg;
    private String userId;
    private String userName;
    private String body;
    private int like;
    private int comment;

    @Override
    public void onCreate(Bundle savedStateInstance) {
        super.onCreate(savedStateInstance);
        setContentView(R.layout.activity_comment);

        mContext = getApplicationContext();
        mToolbar = (Toolbar) findViewById(R.id.toolbar_content);
        mRecyclerView = (RecyclerView) findViewById(R.id.comment_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Intent intent = getIntent();
        commentId = intent.getExtras().getString("commentId");
        userId = intent.getExtras().getString("commentUserId");
        userImg = intent.getExtras().getString("commentUserImg");
        userName = intent.getExtras().getString("commentUserName");
        like = intent.getExtras().getInt("commentLike");
        comment = intent.getExtras().getInt("commentComment");
        createTime = intent.getExtras().getString("commentCreateTime");
        body = intent.getExtras().getString("commentBody");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(userName+" 님의 댓글");

        inputData();
        updateUI();

        editComment =(EditText)findViewById(R.id.editTextComment);
        inputCommentBtn = (Button) findViewById(R.id.input_comment_btn_comment);
        inputCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String text = editComment.getText().toString();
                if(text.length()==0) return;
                Bundle params = new Bundle();
                params.putString("message", text);
                new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/"+ commentId + "/comments",
                        params,
                        HttpMethod.POST,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                new GreenToast(getApplicationContext()).showToast("댓글이 등록되었습니다.");
                                CommentItem commentItem =new CommentItem();
                                commentItem.setComment_able(false);
                                commentItem.setUserName(Profile.getCurrentProfile().getName());
                                commentItem.setComment(0);
                                commentItem.setLike(0);
                                commentItem.setBody(text);
                                commentItem.setUserImg(Profile.getCurrentProfile().getLinkUri().toString());
                                long curTime = System.currentTimeMillis();
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                String str = format.format(new Date(curTime));
                                commentItem.setDate(str);
                                mMultipleItems.add(commentItem);
                                mCommentAdapter.notifyDataSetChanged();
                                //AppLog.i(TAG,  "/"+ id + "/comments"
                            }
                        }
                ).executeAsync();
                editComment.setText("");
            }
        });
        mCommentAdapter.notifyDataSetChanged();
    }

    private void inputData() {
        mMultipleItems = new ArrayList();
        final CommentItem commentItem = new CommentItem();
        commentItem.setUserImg(userImg);
        commentItem.setUserName(userName);
        commentItem.setBody(body);
        commentItem.setDate(createTime);
        commentItem.setLike(like);
        commentItem.setComment(comment);
        commentItem.setComment_able(false);
        mMultipleItems.add(commentItem);

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + commentId + "/comments?fields=like_count,comment_count,message,created_time,user_likes,from",
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
                                commentItem.setLike(Integer.parseInt(cur.getString("like_count")));

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
                                                    mCommentAdapter.notifyDataSetChanged();
                                                }catch (JSONException e){
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                ).executeAsync();
                                mMultipleItems.add(commentItem);
                                mCommentAdapter.notifyDataSetChanged();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();
    }

    private void updateUI() {
        mCommentAdapter = new CommentAdapter();
        mRecyclerView.setAdapter(mCommentAdapter);
    }


    public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public static final int VIEW_TYPE_COMMENT1 = 0;
        public static final int VIEW_TYPE_COMMENT2 = 1;

        public CommentAdapter() {
        }

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_COMMENT1) {
                return CommentHolder.newInstance2(mContext,parent);
            } else {
                return CommentCommentHolder.newInstance(mContext, parent);
            }
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            if (holder.getItemViewType()==VIEW_TYPE_COMMENT1) {
                CommentItem commentItem = (CommentItem) mMultipleItems.get(position);
                if(holder instanceof CommentHolder){
                    ((CommentHolder) holder).onBindView(commentItem);
                }
            } else {
                CommentItem commentItem =(CommentItem)mMultipleItems.get(position);
                if(holder instanceof CommentCommentHolder) {
                    ((CommentCommentHolder) holder).onBindView(commentItem);
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) return VIEW_TYPE_COMMENT1;
            else return VIEW_TYPE_COMMENT2;
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

