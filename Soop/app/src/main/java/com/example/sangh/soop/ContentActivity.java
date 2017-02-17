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

/**
 * Created by sangh on 2017-02-15.
 */

public class ContentActivity extends AppCompatActivity{
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ContentAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    int uniMark;
    int comment ;
    int like ;
    String date;
    String uniName;
    String body;

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

        mAdapter = new ContentAdapter();

        Intent intent = getIntent();
        uniMark =  intent.getExtras().getInt("uniMark");
        comment =  intent.getExtras().getInt("comment");
        like =  intent.getExtras().getInt("like");
        date =  intent.getExtras().getString("date");
        uniName =  intent.getExtras().getString("uniName");
        body =  intent.getExtras().getString("body");

        updateUI();
    }

    private void updateUI(){
        //mContentItems =new ArrayList<>();
       // mAdapter = new ContentAdapter(mContentItems);
       // mRecyclerView.setAdapter(mAdapter);
    }


    public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        public static final int VIEW_TYPE_CONTENT = 0;
        public static final int VIEW_TYPE_COMMENT = 1;


        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_CONTENT) {
                return CommentHolder.newInstance(parent);
            } else {
                return ContentHolder.newInstance(parent);
            }
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position){
            switch (holder.getItemViewType()){
                case VIEW_TYPE_CONTENT:
                    ContentHolder ch = (ContentHolder) holder;

                    break;
                case VIEW_TYPE_COMMENT:
                    CommentHolder cmh = (CommentHolder) holder;

                    break;
            }
        }

        @Override
        public int getItemViewType(int position){
            if(position==0) return VIEW_TYPE_CONTENT;
            else return VIEW_TYPE_COMMENT;
        }

        @Override
        public int getItemCount(){
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
