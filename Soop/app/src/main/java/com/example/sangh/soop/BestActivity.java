package com.example.sangh.soop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.sangh.soop.Adapter.BestAdapter;
import com.example.sangh.soop.Model.ContentItem;
import com.example.sangh.soop.Model.MainItem;

import java.util.ArrayList;

public class BestActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<MainItem> dataSet;
    BestAdapter bestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Best");

        dummyData();
        initView();


    }

    private void initView(){
        recyclerView = (RecyclerView)findViewById(R.id.recycle_best);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bestAdapter = new BestAdapter(this,dataSet);
        recyclerView.setAdapter(bestAdapter);

    }

    private void dummyData(){
        dataSet =new ArrayList<>();
        for(int i=100000; i<110000; i++){
            MainItem mainItem =new MainItem();
            mainItem.setUniMark(R.drawable.stanford);
            mainItem.setUniName("하바드대학교");
            mainItem.setDate("2017년 2월 15일 오후 9:37");
            mainItem.setLike(i-100);
            mainItem.setComment(i-100);
            mainItem.setBody("우리학교 솔직히 지잡대아님? 자꾸 세계 1류 대학인척 하는데 무슨 소리인지 모르겠다는 것은 사실 페이크였고 우리학교는 세계 최고의 대학교인건 솔직히"
                    + "ㅇㅈ? ㄹㅇㅍㅌ ?? ㅂㅂㅂㄱ?? ㅇㅇ ㅇㅇㅈ우리학교 솔직히 지잡대아님? 자꾸 세계 1류 대학인척 하는데 무슨 소리인지 모르겠다는 것은 사실 페이크였고 우리학교는 세계 최고의 대학교인건 솔직히 "
                    +"ㅇㅈ? ㄹㅇㅍㅌ ?? ㅂㅂㅂㄱ?? ㅇㅇ ㅇㅇㅈ");
            dataSet.add(mainItem);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}