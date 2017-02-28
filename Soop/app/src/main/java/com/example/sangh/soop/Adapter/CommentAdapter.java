package com.example.sangh.soop.Adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.example.sangh.soop.Holder.BaseViewHolder;
import com.example.sangh.soop.Holder.CommentCommentHolder;
import com.example.sangh.soop.Holder.CommentHolder;
import com.example.sangh.soop.Model.CommentItem;
import com.example.sangh.soop.Model.MainItem;
import java.util.List;

/**
 * Created by sangh on 2017-02-28.
 */

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<MainItem> mMultipleItems;

    public static final int VIEW_TYPE_COMMENT1 = 0;
    public static final int VIEW_TYPE_COMMENT2 = 1;

    public CommentAdapter(Context con, List<MainItem> items) {
        this.mContext =con;
        this.mMultipleItems = items;
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
