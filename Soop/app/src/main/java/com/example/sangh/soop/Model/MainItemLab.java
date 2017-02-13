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
