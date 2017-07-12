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
import com.philong.danhsachphim.adapter.PopularMovieAdapter;
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

public class PopularMovieFragment extends Fragment {

    private RecyclerView mRecyclerViewPopularMovie;
    private GridLayoutManager mLayoutManager;
    private PopularMovieAdapter mPopularMovieAdapter;
    private List<Movie> mMovieList;
    private ApiInterface mApiInterface;
    private Call<MoviesResponse> call;
    //Load more
    private static int page = 1;
    private boolean isLastPage = false;
    private static final int visibleHold = 5;
    private static final int PAGE_SIZE = 20;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_movie, container, false);
        //Setup recyclerview
        mRecyclerViewPopularMovie = (RecyclerView) view.findViewById(R.id.recyclerViewPopularMovie);
        mRecyclerViewPopularMovie.setHasFixedSize(true);
        mRecyclerViewPopularMovie.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerViewPopularMovie.setLayoutManager(mLayoutManager);
        mMovieList = new ArrayList<>();
        mApiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        //Load more
        mRecyclerViewPopularMovie.addOnScrollListener(recOnScrollListener);
        call = mApiInterface.getPopularMovie(Data.API_KEY, page);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                mMovieList = response.body().getResults();
                mPopularMovieAdapter = new PopularMovieAdapter(getContext(), mMovieList);
                mRecyclerViewPopularMovie.setAdapter(mPopularMovieAdapter);

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
            int visibleItemCount = mLayoutManager.getChildCount();
            int totalItemCount = mLayoutManager.getItemCount();
            int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

            if (!isLastPage) {
                if ((totalItemCount - visibleItemCount) <= (firstVisibleItemPosition + visibleHold)) {
                    page += 1;
                    loadMoreMovie(page);
                }
            }
        }
    };

    private void loadMoreMovie(int page){
        call = mApiInterface.getPopularMovie(Data.API_KEY, page);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if(response.body().getResults().size() < PAGE_SIZE) {
                    isLastPage = true;
                }
                mMovieList.addAll(response.body().getResults());
                mPopularMovieAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });
    }


}
