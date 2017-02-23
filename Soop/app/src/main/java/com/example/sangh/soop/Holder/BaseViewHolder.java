package com.example.sangh.soop.Holder;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;

/**
 * Created by sangh on 2017-02-17.
 */

public abstract class BaseViewHolder<ITEM> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView){
        super(itemView);
    }
    public abstract void onBindView(ITEM item);
}
