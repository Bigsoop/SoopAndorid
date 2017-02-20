package com.example.sangh.soop.Model;

import java.net.URI;
import java.net.URL;

/**
 * Created by sangh on 2017-02-17.
 */

public class CommentItem extends MainItem{
    private String mUserName;
    private String mUserId;
    private String mUserImg;

    public String getUserImg() {
        return mUserImg;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public void setUserImg(String userImg) {
        mUserImg = userImg;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

}
