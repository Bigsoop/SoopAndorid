package com.example.sangh.soop.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sangh.soop.ContentActivity;
import com.example.sangh.soop.Holder.MainHolder;
import com.example.sangh.soop.Model.MainItem;
import com.example.sangh.soop.R;
import com.example.sangh.soop.view.GreenToast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sangh on 2017-02-13.
 */

public class MainFragment extends Fragment{
    private RecyclerView mMainRecyclerView;
    private MainAdapter mAdapter;
    public  List<MainItem> mMainItems;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mMainRecyclerView = (RecyclerView) v.findViewById(R.id.fragment_main);
        mMainRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dummyData();
        updateUI();
        return v;
    }

    private void updateUI(){
        mAdapter = new MainAdapter(mMainItems);
        mMainRecyclerView.setAdapter(mAdapter);
    }

    private void dummyData(){
        mMainItems =new ArrayList<>();
        for(int i=100000; i<110000; i++){
            MainItem mainItem =new MainItem();
            mainItem.setUniMark(R.drawable.stanford);
            mainItem.setUniName("스탠포드대학교");
            mainItem.setDate("2017년 2월 15일 오후 9:37");
            mainItem.setLike(i-100);
            mainItem.setComment(i-100);
            mainItem.setBody("우리학교 솔직히 지잡대아님? 자꾸 세계 1류 대학인척 하는데 무슨 소리인지 모르겠다는 것은 사실 페이크였고 우리학교는 세계 최고의 대학교인건 솔직히"
                   + "ㅇㅈ? ㄹㅇㅍㅌ ?? ㅂㅂㅂㄱ?? ㅇㅇ ㅇㅇㅈ우리학교 솔직히 지잡대아님? 자꾸 세계 1류 대학인척 하는데 무슨 소리인지 모르겠다는 것은 사실 페이크였고 우리학교는 세계 최고의 대학교인건 솔직히 "
                    +"ㅇㅈ? ㄹㅇㅍㅌ ?? ㅂㅂㅂㄱ?? ㅇㅇ ㅇㅇㅈ");
            mMainItems.add(mainItem);
        }
    }



    private class MainAdapter extends RecyclerView.Adapter<MainHolder>{

        public MainAdapter(List<MainItem> MainItems){}

        @Override
        public MainHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater =LayoutInflater.from(getActivity());
            View view =layoutInflater.inflate(R.layout.main_item, parent, false);
            return new MainHolder(view,getContext());
        }

        @Override
        public void onBindViewHolder(MainHolder holder, int position){
            MainItem mainItem = mMainItems.get(position);
            holder.onBindView(mainItem);
        }

        @Override
        public int getItemCount(){
            return mMainItems.size();
        }
    }
}
