package com.philong.danhsachphim.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.philong.danhsachphim.R;
import com.philong.danhsachphim.adapter.TopMovieAdapter;
import com.philong.danhsachphim.model.movie.Movie;
import com.philong.danhsachphim.model.movie.MoviesResponse;
import com.philong.danhsachphim.util.ApiClient;
import com.philong.danhsachphim.util.ApiInterface;
import com.philong.danhsachphim.util.Data;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Long on 6/17/2017.
 */

public class TopMovieFragment extends Fragment {

    private RecyclerView mRecyclerViewTopMovie;
    private TopMovieAdapter mTopMovieAdapter;
    private List<Movie> mTopMovieList;
    private ApiInterface mApiInterface;
    private Call<MoviesResponse> call;
    private GridLayoutManager mGridLayoutManager;
    //Load more
    private static int page = 1;
    private static final int PAGE_SIZE = 20;
    private static final int visibleHold = 5;
    private boolean isLastPage = false;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_movie, container, false);
        //Setup recyclerview
        mTopMovieList = new ArrayList<>();
        mRecyclerViewTopMovie = (RecyclerView) view.findViewById(R.id.recyclerViewTopMovie);
        mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerViewTopMovie.setLayoutManager(mGridLayoutManager);
        mRecyclerViewTopMovie.setHasFixedSize(true);
        mRecyclerViewTopMovie.setItemAnimator(new DefaultItemAnimator());
        mApiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        //Load more
        mRecyclerViewTopMovie.addOnScrollListener(recOnScrollListener);
        call = mApiInterface.getTopMovieRated(Data.API_KEY, page);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                mTopMovieList = response.body().getResults();
                mTopMovieAdapter = new TopMovieAdapter(getContext(), mTopMovieList);
                mRecyclerViewTopMovie.setAdapter(mTopMovieAdapter);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });
        return view;
    }

    private RecyclerView.OnScrollListener recOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItem = mGridLayoutManager.getChildCount();
            int totalItem = mGridLayoutManager.getItemCount();
            int firstItem = mGridLayoutManager.findFirstVisibleItemPosition();
            System.out.println("***********");
            System.out.println("visible + first: " + (visibleItem + firstItem));
            System.out.println("total: " + totalItem);
            System.out.println("***********");
            if(!isLastPage){
                if((totalItem - visibleItem) <= (firstItem + visibleHold)){
                    page += 1;
                    loadMoreMovie(page);
                }
            }
        }
    };

    private void loadMoreMovie(int page){
        call = mApiInterface.getTopMovieRated(Data.API_KEY, page);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if(response.body().getResults().size() <= PAGE_SIZE) {
                    isLastPage = true;
                }
                mTopMovieList.addAll(response.body().getResults());
                mTopMovieAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });
    }
}
