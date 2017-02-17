package com.example.sangh.soop.Adapter;

import android.support.v7.widget.RecyclerView;

import com.example.sangh.soop.Holder.BaseViewHolder;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by sangh on 2017-02-17.
 */

public abstract class MultiItemAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private List<Row<?>> mRows = new ArrayList<>();

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position){
        holder.onBindView(mRows.get(position));
    }

    @SuppressWarnings("unchecked")
    public <ITEM> ITEM getItem(int position){
        return (ITEM) mRows.get(position).getItem();
    }

    public void setRows(List<Row<?>> mRows){
        mRows.clear();
        mRows.addAll(mRows);
    }

    @Override
    public int getItemCount(){
        return mRows.size();
    }

    @Override
    public int getItemViewType(int position){
        return mRows.get(position).getItemViewType();
    }

    public static class Row<ITEM>{
        private ITEM item;
        private int itemViewType;

        private Row(ITEM item, int itemViewType){
            this.item =item;
            this.itemViewType = itemViewType;
        }

        public static <T> Row<T> create(T item, int itemViewType){
            return new Row<>(item,itemViewType);
        }

        public ITEM getItem(){
            return item;
        }

        public int getItemViewType(){
            return itemViewType;
        }
    }
}
