package com.philong.danhsachphim.model.video;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Long on 6/17/2017.
 */

public class Result {

    @SerializedName("id")
    private String mId;
    @SerializedName("iso_639_1")
    private String mIso;
    @SerializedName("iso_3166_1")
    private String mIso1;
    @SerializedName("key")
    private String mKey;
    @SerializedName("name")
    private String mName;
    @SerializedName("site")
    private String mSite;
    @SerializedName("Size")
    private int mSize;
    @SerializedName("type")
    private String mType;

    public Result(String id, String iso, String iso1, String key, String name, String site, int size, String type) {
        mId = id;
        mIso = iso;
        mIso1 = iso1;
        mKey = key;
        mName = name;
        mSite = site;
        mSize = size;
        mType = type;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getIso() {
        return mIso;
    }

    public void setIso(String iso) {
        mIso = iso;
    }

    public String getIso1() {
        return mIso1;
    }

    public void setIso1(String iso1) {
        mIso1 = iso1;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSite() {
        return mSite;
    }

    public void setSite(String site) {
        mSite = site;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        mSize = size;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}
