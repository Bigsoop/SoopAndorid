package com.example.sangh.soop.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.sangh.soop.Holder.BestHolder;
import com.example.sangh.soop.Model.MainItem;
import com.example.sangh.soop.R;

import java.util.ArrayList;

/**
 * Created by koohanmo on 2017-02-19.
 */

public class BestAdapter extends RecyclerView.Adapter<BestHolder>{

    private Context mcon;
    private ArrayList<MainItem> dataSet;
    private Typeface typeface;

    public BestAdapter(Context context, ArrayList<MainItem> data){
        this.mcon=context;
        this.dataSet=data;
        typeface= Typeface.createFromAsset(mcon.getAssets(), "SHOWG.TTF");
    }


    @Override
    public BestHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater =LayoutInflater.from(mcon);
        View view =layoutInflater.inflate(R.layout.item_best, parent, false);
        return new BestHolder(view,mcon);
    }

    @Override
    public void onBindViewHolder(BestHolder holder, int position){
        holder.onBindView(dataSet.get(position));
        holder.setRank(position+1);
        holder.setTypeFace(typeface);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
