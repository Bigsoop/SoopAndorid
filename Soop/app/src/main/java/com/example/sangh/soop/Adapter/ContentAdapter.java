package com.example.sangh.soop.Adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.example.sangh.soop.Holder.BaseViewHolder;
import com.example.sangh.soop.Holder.CommentHolder;
import com.example.sangh.soop.Holder.ContentHolder;
import com.example.sangh.soop.Model.CommentItem;
import com.example.sangh.soop.Model.ContentItem;
import com.example.sangh.soop.Model.MainItem;
import java.util.List;

/**
 * Created by sangh on 2017-02-28.
 */

public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<MainItem> mMultipleItems;

    public static final int VIEW_TYPE_CONTENT = 0;
    public static final int VIEW_TYPE_COMMENT = 1;

    public ContentAdapter(Context con, List<MainItem> items) {
        this.mContext=con;
        this.mMultipleItems= items;
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


