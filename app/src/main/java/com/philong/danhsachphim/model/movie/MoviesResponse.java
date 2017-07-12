package com.philong.danhsachphim.model.movie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Long on 6/17/2017.
 */

public class MoviesResponse implements Parcelable{

    @SerializedName("page")
    private int mPage;
    @SerializedName("total_results")
    private int mTotalResults;
    @SerializedName("total_pages")
    private int mTotalPages;
    @SerializedName("results")
    private List<Movie> mResults;

    protected MoviesResponse(Parcel in) {
        mPage = in.readInt();
        mTotalResults = in.readInt();
        mTotalPages = in.readInt();
        mResults = in.createTypedArrayList(Movie.CREATOR);
    }

    public static final Creator<MoviesResponse> CREATOR = new Creator<MoviesResponse>() {
        @Override
        public MoviesResponse createFromParcel(Parcel in) {
            return new MoviesResponse(in);
        }

        @Override
        public MoviesResponse[] newArray(int size) {
            return new MoviesResponse[size];
        }
    };

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(int totalResults) {
        mTotalResults = totalResults;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(int totalPages) {
        mTotalPages = totalPages;
    }

    public List<Movie> getResults() {
        return mResults;
    }

    public void setResults(List<Movie> results) {
        mResults = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mPage);
        dest.writeInt(mTotalResults);
        dest.writeInt(mTotalPages);
        dest.writeTypedList(mResults);
    }
}
