package com.example.sangh.soop.Model;

import com.example.sangh.soop.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by sangh on 2017-02-13.
 */

public class MainItem {

    private String id;
    private String mUniMarkUrl;
    private String mUniMark;
    private String mUniName;
    private String mDate;
    private String mBody;
    private int mLike;
    private int mShare;
    private int mComment;

    public MainItem(){}

    public boolean setJsonObject(JSONObject jsonObject){
        try {
            this.setId(jsonObject.getString("id"));
            this.setLike(Integer.parseInt(jsonObject.getString("likes")));
            this.setComment(Integer.parseInt(jsonObject.getString("comments")));
            this.setShare(Integer.parseInt(jsonObject.getString("shares")));
            this.setmDate(jsonObject.getString("created_time"));
            this.setUniName(Constant.UniName.values()[Integer.parseInt(jsonObject.getString("univKey"))]+"");
            this.setUniMarkUrl("/"+Constant.uniImg[Integer.parseInt(jsonObject.getString("univKey"))]+"/picture");
            this.setBody(jsonObject.getString("message").trim());
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUniMark() {
        return mUniMark;
    }

    public void setUniMark(String uniMark) {
        mUniMark = uniMark;
    }

    public String getUniName() {
        return mUniName;
    }

    public void setUniName(String uniName) {
        mUniName = uniName;
    }

    public String getDate() {return mDate;}

    public void setDate(String date) {
        mDate = date;
    }

    public String getUniMarkUrl() {
        return mUniMarkUrl;
    }

    public void setUniMarkUrl(String uniMarkUrl) {
        mUniMarkUrl = uniMarkUrl;
    }

    public int getLike() {
        return mLike;
    }

    public void setLike(int like) {
        mLike = like;
    }

    public int getComment() {
        return mComment;
    }

    public void setComment(int comment) {
        mComment = comment;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getShare() {
        return mShare;
    }

    public void setShare(int mShare) {
        this.mShare = mShare;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }
}
