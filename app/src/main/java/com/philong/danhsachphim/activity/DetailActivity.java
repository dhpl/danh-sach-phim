package com.philong.danhsachphim.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.philong.danhsachphim.R;
import com.philong.danhsachphim.model.movie.Movie;
import com.philong.danhsachphim.model.video.Result;
import com.philong.danhsachphim.model.video.Video;
import com.philong.danhsachphim.util.ApiClient;
import com.philong.danhsachphim.util.ApiInterface;
import com.philong.danhsachphim.util.Data;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends YouTubeBaseActivity{

    private YouTubePlayerView mYouTubePlayerView;
    private ImageView imgVideo;
    private TextView txtTenVideo;
    private TextView txtRelease;
    private TextView txtNoiDungVideo;

    private ApiInterface mApiInterface;
    private Call<Video> call;

    private boolean isFull = false;

    public static Intent newIntent(Context context, Movie movie){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("Movie", movie);
        return intent;
    }

    public Movie getIntenMovie(){
        if(getIntent() != null){
            return getIntent().getParcelableExtra("Movie");
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);

        //init view
        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubePlayer);
        imgVideo = (ImageView) findViewById(R.id.imgVideo);
        txtTenVideo = (TextView) findViewById(R.id.txtTenVideo);
        txtRelease = (TextView) findViewById(R.id.txtReleaseVideo);
        txtNoiDungVideo = (TextView) findViewById(R.id.txtNoiDungVideo);
        Movie movie = getIntenMovie();
        if(movie != null){
            Picasso.with(this).load("http://image.tmdb.org/t/p/w500/" + movie.getPosterPath()).error(R.drawable.placeholder).into(imgVideo);
            txtTenVideo.setText(movie.getTitle());
            txtRelease.setText(movie.getReleaseDate());
            txtNoiDungVideo.setText(movie.getOverview());
        }

        //Setup youtube
        if(movie != null){
            mApiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
            call = mApiInterface.getVideos(movie.getId(), Data.API_KEY);
            call.enqueue(new Callback<Video>() {
                @Override
                public void onResponse(Call<Video> call, final Response<Video> response) {
                    mYouTubePlayerView.initialize(Data.API_KEY_YOUTUBE, new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                            if(!b){
                                Result result = response.body().getResultList().get(0);
                                if(result != null){
                                    youTubePlayer.setFullscreen(false);
                                    youTubePlayer.cueVideo(result.getKey());
                                }
                            }
                        }
                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                        }
                    });
                }

                @Override
                public void onFailure(Call<Video> call, Throwable t) {

                }
            });

        }
    }





}
