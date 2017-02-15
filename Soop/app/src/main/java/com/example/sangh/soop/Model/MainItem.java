package com.example.sangh.soop.Model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by sangh on 2017-02-13.
 */

public class MainItem {
    private UUID id;
    private String mUniName;
    private String mDate;
    private int mUniMark;
    private String mLike;
    private String mComment;
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

    public String getDate() {return mDate;}

    public void setDate(String date) {
        mDate = date;
    }

    public int getUniMark() {
        return mUniMark;
    }

    public void setUniMark(int uniMark) {
        mUniMark = uniMark;
    }

    public String getLike() {
        return mLike;
    }

    public void setLike(String like) {
        mLike = like;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }
}
