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
        for(int i=0; i<20; i++){
            MainItem mainItem =new MainItem();
            mainItem.setUniName("스탠포드대학교");
            mainItem.setOrder("#"+i+"번째 글");
            mainItem.setLike("123456");
            mainItem.setComment("654321");
            mainItem.setBody("우리학교 솔직히 지잡대아님? 자꾸 세계 1류 우리학교 솔직히 지잡대아님? 자꾸 세계 1류 대학 인척하는데 왜그러는지 ");
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
