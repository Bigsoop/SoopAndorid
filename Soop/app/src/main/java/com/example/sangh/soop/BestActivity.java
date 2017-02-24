package com.example.sangh.soop;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.example.sangh.soop.Adapter.BestAdapter;
import com.example.sangh.soop.Model.MainItem;
import com.example.sangh.soop.view.GreenToast;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BestActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<MainItem> dataSet = new ArrayList<>();
    BestAdapter bestAdapter;
    private final String TAG = "BestActivity";
    private Handler mHandler;
    private final int MSG_DATACHANGE =0;
    private final int MSG_ERR_TOAST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Best");

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 0: bestAdapter.notifyDataSetChanged(); break;
                    case 1: new GreenToast(getApplicationContext()).showToast("네트워크 연결 상태를 확인해주세요"); break;
                }
                return false;
            }
        });

        initView();
        requestBestData("");
    }

    private void initView(){
        recyclerView = (RecyclerView)findViewById(R.id.recycle_best);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bestAdapter = new BestAdapter(this,dataSet);
        recyclerView.setAdapter(bestAdapter);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestBestData(String Best){
        try {
            NetworkRequests.getInstance().getAriticle(Constant.BEST, "", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    mHandler.sendEmptyMessage(MSG_ERR_TOAST);
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    AppLog.i(TAG,"RESPONSE");
                    try {
                        JSONArray responseJson = new JSONArray(response.body().string());
                        for(int i=0; i<responseJson.length(); i++){
                            JSONObject cur = responseJson.getJSONObject(i);
                            final MainItem mainItem = new MainItem();
                            if(!mainItem.setJsonObject(cur)) continue;
                            new GraphRequest(
                                    AccessToken.getCurrentAccessToken(),
                                    mainItem.getUniMarkUrl()+"?redirect=false",
                                    null,
                                    HttpMethod.GET,
                                    new GraphRequest.Callback() {
                                        public void onCompleted(GraphResponse response) {
                                            try{
                                                JSONObject userImg = response.getJSONObject();
                                                JSONObject data = userImg.getJSONObject("data");
                                                mainItem.setUniMark(data.getString("url"));
                                                bestAdapter.notifyDataSetChanged();
                                            }catch (JSONException e){
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                            ).executeAsync();
                            dataSet.add(mainItem);

                        }
                        AppLog.i(TAG, responseJson.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        mHandler.sendEmptyMessage(MSG_ERR_TOAST);
                    }
                    mHandler.sendEmptyMessage(0);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_ERR_TOAST);
        }
    }

}