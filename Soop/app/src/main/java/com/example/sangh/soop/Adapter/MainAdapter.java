package com.example.sangh.soop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.sangh.soop.Holder.MainHolder;
import com.example.sangh.soop.Model.MainItem;
import com.example.sangh.soop.R;
import java.util.ArrayList;

/**
 * Created by koohanmo on 2017-02-19.
 */


public class MainAdapter extends RecyclerView.Adapter<MainHolder>{

    Context mcon;
    ArrayList<MainItem> dataSet;

    public MainAdapter(Context mcon, ArrayList<MainItem> MainItems){
        this.mcon =mcon;
        this.dataSet=MainItems;

    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater =LayoutInflater.from(mcon);
        View view =layoutInflater.inflate(R.layout.main_item, parent, false);
        return new MainHolder(view,mcon);
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position){
        MainItem mainItem = dataSet.get(position);
        holder.onBindView(mainItem);
    }

    @Override
    public int getItemCount(){
        return dataSet.size();
    }
}