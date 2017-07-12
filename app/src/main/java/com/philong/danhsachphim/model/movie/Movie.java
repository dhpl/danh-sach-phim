package com.philong.danhsachphim.model.movie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 6/17/2017.
 */

public class Movie implements Parcelable{

    @SerializedName("vote_count")
    private int mVoteCount;
    @SerializedName("id")
    private int mId;
    @SerializedName("video")
    private boolean mVideo;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("popularity")
    private double mPopularity;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @SerializedName("original_title")
    private String mOriginalTitle;
    @SerializedName("genre_ids")
    private List<Integer> mGenreIds = new ArrayList<>();
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("adult")
    private String mAdult;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("release_date")
    private String mReleaseDate;

    public Movie(int voteCount, int id, boolean video, String title, double popularity, String posterPath, String originalLanguage, String originalTitle, List<Integer> genreIds, String backdropPath, String adult, String overview, String releaseDate) {
        mVoteCount = voteCount;
        mId = id;
        mVideo = video;
        mTitle = title;
        mPopularity = popularity;
        mPosterPath = posterPath;
        mOriginalLanguage = originalLanguage;
        mOriginalTitle = originalTitle;
        mGenreIds = genreIds;
        mBackdropPath = backdropPath;
        mAdult = adult;
        mOverview = overview;
        mReleaseDate = releaseDate;
    }

    protected Movie(Parcel in) {
        mVoteCount = in.readInt();
        mId = in.readInt();
        mVideo = in.readByte() != 0;
        mTitle = in.readString();
        mPopularity = in.readDouble();
        mPosterPath = in.readString();
        mOriginalLanguage = in.readString();
        mOriginalTitle = in.readString();
        mBackdropPath = in.readString();
        mAdult = in.readString();
        mOverview = in.readString();
        mReleaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(int voteCount) {
        mVoteCount = voteCount;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public boolean isVideo() {
        return mVideo;
    }

    public void setVideo(boolean video) {
        mVideo = video;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(double popularity) {
        mPopularity = popularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public List<Integer> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        mGenreIds = genreIds;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public String getAdult() {
        return mAdult;
    }

    public void setAdult(String adult) {
        mAdult = adult;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mVoteCount);
        dest.writeInt(mId);
        dest.writeByte((byte) (mVideo ? 1 : 0));
        dest.writeString(mTitle);
        dest.writeDouble(mPopularity);
        dest.writeString(mPosterPath);
        dest.writeString(mOriginalLanguage);
        dest.writeString(mOriginalTitle);
        dest.writeString(mBackdropPath);
        dest.writeString(mAdult);
        dest.writeString(mOverview);
        dest.writeString(mReleaseDate);
    }
}
