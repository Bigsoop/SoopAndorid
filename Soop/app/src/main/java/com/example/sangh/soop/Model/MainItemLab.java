package com.example.sangh.soop.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sangh on 2017-02-13.
 */

public class MainItemLab {
    private static MainItemLab sMainItemLab;

    private List<MainItem> mMainItems;

    public static MainItemLab get(Context context){
        if(sMainItemLab==null){
            sMainItemLab = new MainItemLab(context);
        }
        return sMainItemLab;
    }

    private MainItemLab(Context context){
        mMainItems =new ArrayList<>();
        for(int i=100000; i<110000; i++){
            MainItem mainItem =new MainItem();
            mainItem.setUniName("스탠포드대학교");
            mainItem.setDate("2017년 2월 15일 오후 9:37");
            mainItem.setLike("123");
            mainItem.setComment("65");
            mainItem.setBody("우리학교 솔직히 지잡대아님? 자꾸 세계 1류 대학인척 하는데 무슨 소리인지 모르겠다는 것은 사실 페이크였고 우리학교는 세계 최고의 대학교인건 솔직히" +
                    "ㅇㅈ? ㄹㅇㅍㅌ ?? ㅂㅂㅂㄱ?? ㅇㅇ ㅇㅇㅈ");
            mMainItems.add(mainItem);
        }
    }

    public List<MainItem> getMainItems(){
        return mMainItems;
    }

    public MainItem getMainItem(UUID id){
        for(MainItem item : mMainItems){
            if(item.getId().equals(id)){
               return item;
            }
        }
        return null;
    }
}
