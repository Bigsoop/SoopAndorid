package com.example.sangh.soop.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sangh.soop.ContentActivity;
import com.example.sangh.soop.Model.MainItem;
import com.example.sangh.soop.Model.MainItemLab;
import com.example.sangh.soop.R;

import java.util.List;


/**
 * Created by sangh on 2017-02-13.
 */

public class MainFragment extends Fragment{

    private RecyclerView mMainRecyclerView;
    private MainAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mMainRecyclerView = (RecyclerView) v.findViewById(R.id.fragment_main);
        mMainRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }


    private void updateUI(){
        MainItemLab mainItemLab = MainItemLab.get(getActivity());
        List<MainItem> mainItems = mainItemLab.getMainItems();

        mAdapter = new MainAdapter(mainItems);
        mMainRecyclerView.setAdapter(mAdapter);
    }

    private class MainHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private MainItem mItem;
        private ImageView mUniMark;
        private TextView mDate;
        private TextView mUniName;
        private TextView mLike;
        private TextView mComment;
        private TextView mBody;

        public MainHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            //mUniMark = (ImageView) itemView.findViewById(R.id.uni_mark);
            mDate =(TextView) itemView.findViewById(R.id.main_date);
            mUniName = (TextView) itemView.findViewById(R.id.uni_name);
            mLike = (TextView) itemView.findViewById(R.id.like);
            mComment = (TextView) itemView.findViewById(R.id.comment);
            mBody = (TextView) itemView.findViewById(R.id.body);
        }

        public void bindMainItem(MainItem item){
            mItem = item;
            //mUniMark.setImageResource(mItem.getUniMark());
            mUniName.setText(mItem.getUniName());
            mLike.setText(mItem.getLike());
            mComment.setText(mItem.getComment());
            mBody.setText(mItem.getBody());
            mDate.setText(mItem.getDate());
        }

        @Override
        public void onClick(View view){
            Toast.makeText(getActivity(), "선택됨!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), ContentActivity.class);
            startActivity(intent);
        }
    }

    private class MainAdapter extends RecyclerView.Adapter<MainHolder>{
        private List<MainItem> mMainItems;

        public MainAdapter(List<MainItem> MainItems){
            mMainItems = MainItems;
        }

        @Override
        public MainHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater =LayoutInflater.from(getActivity());
            View view =layoutInflater.inflate(R.layout.main_item, parent, false);
            return new MainHolder(view);
        }

        @Override
        public void onBindViewHolder(MainHolder holder, int position){
            MainItem mainItem = mMainItems.get(position);
            holder.bindMainItem(mainItem);
        }

        @Override
        public int getItemCount(){
            return mMainItems.size();
        }
    }

}
