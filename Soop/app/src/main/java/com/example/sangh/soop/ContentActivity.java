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
import android.widget.Button;
import android.widget.EditText;
import com.example.sangh.soop.Adapter.ContentAdapter;
import com.example.sangh.soop.Model.CommentItem;
import com.example.sangh.soop.Model.ContentItem;
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
 * Created by sangh on 2017-02-15.
 */

public class ContentActivity extends AppCompatActivity {

    private final String TAG ="ContentActivity";
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ContentAdapter mAdapter;
    private List<MainItem> mMultipleItems;
    private Context mContext;
    int comment;
    int like;
    int share;
    String uniMark;
    String id;
    String date;
    String uniName;
    String body;
    Button inputCommentBtn;
    EditText editComment;
    boolean loginStatus;
    @Override
    protected void onCreate(Bundle savedSavedInstance) {
        super.onCreate(savedSavedInstance);
        setContentView(R.layout.activity_content);
        loginStatus=User.getIsLogin(this);

        mContext=getApplicationContext();
        mToolbar = (Toolbar) findViewById(R.id.toolbar_content);
        mRecyclerView = (RecyclerView) findViewById(R.id.content_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Intent intent = getIntent();
        id = intent.getExtras().getString("id");
        uniMark = intent.getExtras().getString("uniMark");
        comment = intent.getExtras().getInt("comment");
        like = intent.getExtras().getInt("like");
        date = intent.getExtras().getString("date");
        uniName = intent.getExtras().getString("uniName");
        body = intent.getExtras().getString("body");
        share = intent.getExtras().getInt("share");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(uniName+" 대나무숲의 글");

        inputData();
        updateUI();

        editComment =(EditText)findViewById(R.id.editText);
        inputCommentBtn = (Button) findViewById(R.id.input_comment_btn);
        inputCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginStatus) {
                    final String text = editComment.getText().toString();
                    if (text.length() == 0) return;
                    Bundle params = new Bundle();
                    params.putString("message", text);
                    new GraphRequest(
                            AccessToken.getCurrentAccessToken(),
                            "/" + id + "/comments",
                            params,
                            HttpMethod.POST,
                            new GraphRequest.Callback() {
                                public void onCompleted(GraphResponse response) {
                                    new GreenToast(getApplicationContext()).showToast("댓글이 등록되었습니다.");
                                    CommentItem commentItem = new CommentItem();
                                    commentItem.setComment_able(false);
                                    commentItem.setUserName(Profile.getCurrentProfile().getName());
                                    commentItem.setComment(0);
                                    commentItem.setLike(0);
                                    commentItem.setBody(text);
                                    commentItem.setUserImg(Profile.getCurrentProfile().getLinkUri().toString());
                                    long curTime = System.currentTimeMillis();
                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd' 'hh:mm:ss");
                                    String str = format.format(new Date(curTime));
                                    commentItem.setDate(str);
                                    mMultipleItems.add(commentItem);
                                    mAdapter.notifyDataSetChanged();
                                    //AppLog.i(TAG,  "/"+ id + "/comments"
                                }
                            }
                    ).executeAsync();
                    editComment.setText("");
                }
                else{
                    new GreenToast(getApplicationContext()).showToast("댓글을 입력하려면 로그인이 필요합니다.");
                    editComment.setText("");
                }
            }
        });
        mAdapter.notifyDataSetChanged();
    }

    private void updateUI() {
        mAdapter = new ContentAdapter(mContext, mMultipleItems);
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

        if (loginStatus) {
                new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/" + id + "/comments?fields=like_count,comment_count,message,created_time,user_likes,from",
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                JSONObject jo = response.getJSONObject();
                                try {
                                    JSONArray ja = jo.getJSONArray("data");

                                    for (int i = 0; i < ja.length(); i++) {
                                        final CommentItem commentItem = new CommentItem();
                                        JSONObject cur = ja.getJSONObject(i);
                                        JSONObject from = cur.getJSONObject("from");
                                        commentItem.setUserId(from.getString("id"));
                                        commentItem.setUserName(from.getString("name"));
                                        commentItem.setId(cur.getString("id"));
                                        commentItem.setDate(Common.dateFormat(cur.getString("created_time")));
                                        commentItem.setBody(cur.getString("message"));
                                        commentItem.setLike(Integer.parseInt(cur.getString("like_count")));
                                        commentItem.setComment(Integer.parseInt(cur.getString("comment_count")));

                                        new GraphRequest(
                                                AccessToken.getCurrentAccessToken(),
                                                "/" + commentItem.getUserId() + "/picture?redirect=false",
                                                null,
                                                HttpMethod.GET,
                                                new GraphRequest.Callback() {
                                                    public void onCompleted(GraphResponse response) {
                                                        try {
                                                            AppLog.i(TAG, "/" + commentItem.getUserId() + "/picture");
                                                            JSONObject userImg = response.getJSONObject();
                                                            JSONObject data = userImg.getJSONObject("data");
                                                            commentItem.setUserImg(data.getString("url"));
                                                            mAdapter.notifyDataSetChanged();
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                }
                                        ).executeAsync();
                                        mMultipleItems.add(commentItem);
                                        mAdapter.notifyDataSetChanged();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                ).executeAsync();
        }
        else{
            new GreenToast(getApplicationContext()).showToast("로그인을 하면 댓글을 볼 수 있습니다.");
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