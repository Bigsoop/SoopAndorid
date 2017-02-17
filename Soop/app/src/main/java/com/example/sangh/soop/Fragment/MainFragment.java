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
            mUniMark = (ImageView) itemView.findViewById(R.id.uni_mark);
            mDate =(TextView) itemView.findViewById(R.id.main_date);
            mUniName = (TextView) itemView.findViewById(R.id.uni_name);
            mLike = (TextView) itemView.findViewById(R.id.like);
            mComment = (TextView) itemView.findViewById(R.id.comment);
            mBody = (TextView) itemView.findViewById(R.id.body);

        }

        public void onBindView(MainItem item){
            mItem = item;
            mUniMark.setImageResource(mItem.getUniMark());
            mUniName.setText(mItem.getUniName());
            mLike.setText(mItem.getLike()+"");
            mComment.setText(mItem.getComment()+"");
            mBody.setText(mItem.getBody());
            mDate.setText(mItem.getDate());
        }

        @Override
        public void onClick(View view){
            new GreenToast(getActivity()).showToast("선택됨!");
            Intent intent = new Intent(getActivity(), ContentActivity.class);
            intent.putExtra("uniMark",mItem.getUniMark());
            intent.putExtra("date",mItem.getDate());
            intent.putExtra("uniName",mItem.getUniName());
            intent.putExtra("like",mItem.getLike());
            intent.putExtra("comment",mItem.getComment());
            intent.putExtra("body",mItem.getBody());
            startActivity(intent);

        }
    }

    private class MainAdapter extends RecyclerView.Adapter<MainHolder>{

        public MainAdapter(List<MainItem> MainItems){}

        @Override
        public MainHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater =LayoutInflater.from(getActivity());
            View view =layoutInflater.inflate(R.layout.main_item, parent, false);
            return new MainHolder(view);
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
