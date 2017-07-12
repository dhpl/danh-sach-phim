package com.philong.danhsachphim.util;

import com.philong.danhsachphim.model.movie.MoviesResponse;
import com.philong.danhsachphim.model.video.Video;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Long on 6/17/2017.
 */

public interface ApiInterface {

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopMovieRated(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/popular/")
    Call<MoviesResponse> getPopularMovie(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/{id}/videos")
    Call<Video> getVideos(@Path("id") int id, @Query("api_key") String apiKey);



}
