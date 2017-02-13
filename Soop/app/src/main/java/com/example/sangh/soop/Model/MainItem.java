package com.example.sangh.soop.Model;

import java.util.UUID;

/**
 * Created by sangh on 2017-02-13.
 */

public class MainItem {
    private UUID id;
    private String mUniName;
    private String mOrder;
    private int mUniMark;
    private int mLike;
    private int mComment;
    private String mBody;

    public UUID getId() {
        return id;
    }

    public String getUniName() {
        return mUniName;
    }

    public void setUniName(String uniName) {
        mUniName = uniName;
    }

    public String getOrder() {
        return mOrder;
    }

    public void setOrder(String order) {
        mOrder = order;
    }

    public int getUniMark() {
        return mUniMark;
    }

    public void setUniMark(int uniMark) {
        mUniMark = uniMark;
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
}
