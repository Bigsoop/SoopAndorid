package com.example.sangh.soop.Model;

/**
 * Created by sangh on 2017-02-17.
 */

public class CommentItem {
    private int mUserImg;
    private String mUserName;
    private String mDate;
    private String mBody;
    private int mLike;
    private int mComment;

    public int getUserImg() {
        return mUserImg;
    }

    public void setUserImg(int userImg) {
        mUserImg = userImg;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getDate() {return mDate;}

    public void setDate(String date) {
        mDate = date;
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

    public int getType(){
        return 1;
    }
}
