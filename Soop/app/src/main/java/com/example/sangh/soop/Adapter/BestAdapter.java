package com.example.sangh.soop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sangh.soop.Holder.MainHolder;
import com.example.sangh.soop.Model.ContentItem;
import com.example.sangh.soop.Model.MainItem;
import com.example.sangh.soop.R;

import java.util.ArrayList;

/**
 * Created by koohanmo on 2017-02-19.
 */

public class BestAdapter extends RecyclerView.Adapter<MainHolder>{

    private Context mcon;
    private ArrayList<MainItem> dataSet;

    public BestAdapter(Context context, ArrayList<MainItem> data){
        this.mcon=context;
        this.dataSet=data;
    }


    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater =LayoutInflater.from(mcon);
        View view =layoutInflater.inflate(R.layout.item_best, parent, false);
        return new MainHolder(view,mcon);
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position){
        holder.onBindView(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
