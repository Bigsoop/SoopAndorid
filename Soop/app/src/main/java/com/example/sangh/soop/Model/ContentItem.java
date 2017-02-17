package com.example.sangh.soop.Model;

/**
 * Created by sangh on 2017-02-17.
 */

public class ContentItem {

    private int mUniMark;
    private String mUniName;
    private String mDate;
    private String mBody;
    private int mLike;
    private int mComment;
    private int mShare;

    public int getShare() { return mShare; }
    public void setShare(int share) { mShare = share; }

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
