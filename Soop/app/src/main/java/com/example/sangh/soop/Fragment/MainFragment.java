package com.example.sangh.soop.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.sangh.soop.Adapter.MainAdapter;
import com.example.sangh.soop.AppLog;
import com.example.sangh.soop.Common;
import com.example.sangh.soop.Constant;
import com.example.sangh.soop.Model.MainItem;
import com.example.sangh.soop.NetworkRequests;
import com.example.sangh.soop.R;
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

/**
 * Created by sangh on 2017-02-13.
 */

public class MainFragment extends Fragment{

    private final String TAG = "MainFragment";

    private RecyclerView mMainRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MainAdapter mAdapter;
    private ArrayList<MainItem> mMainItems=new ArrayList<>();;

    private Handler mHandler;
    private final int MSG_DATACHANGE =0;
    private final int MSG_ERR_TOAST = 1;

    private boolean requireUpdate = true;
    private String lastTime = "2030-1-1 12:00";


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.main_swipe_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
                lastTime = "2030-1-1 12:00";
                mMainItems.clear();
                requestMainData(lastTime);
                updateUI();
                new GreenToast(getActivity()).showToast("업데이트 되었습니다");
            }
        });

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 0: mAdapter.notifyDataSetChanged(); break;
                    case 1: new GreenToast(getActivity()).showToast("네트워크 연결 상태를 확인해주세요"); break;
                }
                return false;
            }
        });

        mMainRecyclerView = (RecyclerView) v.findViewById(R.id.fragment_main);
        mMainRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMainRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                int offset = recyclerView.computeVerticalScrollOffset();
                int extent = recyclerView.computeVerticalScrollExtent();
                int range = recyclerView.computeVerticalScrollRange();

                if(range-offset < extent*3 && requireUpdate){
                    requestMainData(lastTime);
                }
            }
        });

        requestMainData(lastTime);
        updateUI();
        return v;
    }

    private void updateUI(){
        mAdapter = new MainAdapter(getContext(),mMainItems);
        mMainRecyclerView.setAdapter(mAdapter);
    }


    private void requestMainData(String date){
        if(!requireUpdate) return;
        requireUpdate=false;
        try {
            NetworkRequests.getInstance().getAriticle(Constant.MAIN, date, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    mHandler.sendEmptyMessage(MSG_ERR_TOAST);
                    requireUpdate=true;
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
                                                mAdapter.notifyDataSetChanged();
                                            }catch (JSONException e){
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                            ).executeAsync();
                            mMainItems.add(mainItem);
                            if(i== responseJson.length()-1) lastTime=mainItem.getDate();
                        }
                        AppLog.i(TAG, responseJson.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        mHandler.sendEmptyMessage(MSG_ERR_TOAST);
                    }
                    requireUpdate=true;
                    mHandler.sendEmptyMessage(0);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_ERR_TOAST);
            requireUpdate=true;
        }
    }
}
