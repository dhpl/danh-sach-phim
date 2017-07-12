package com.philong.danhsachphim.model.video;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Long on 6/17/2017.
 */

public class Video {

    @SerializedName("id")
    private int mId;
    @SerializedName("results")
    private List<Result> mResultList;

    public Video(int id, List<Result> resultList) {
        mId = id;
        mResultList = resultList;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public List<Result> getResultList() {
        return mResultList;
    }

    public void setResultList(List<Result> resultList) {
        mResultList = resultList;
    }
}
