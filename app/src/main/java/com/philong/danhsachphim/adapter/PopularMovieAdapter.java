package com.philong.danhsachphim.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.philong.danhsachphim.R;
import com.philong.danhsachphim.activity.DetailActivity;
import com.philong.danhsachphim.model.movie.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Long on 6/17/2017.
 */

public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.PopularMovieViewHolder>{

    private Context mContext;
    private List<Movie> mPopularMovieList;

    public PopularMovieAdapter(Context context, List<Movie> movieList) {
        mContext = context;
        mPopularMovieList = movieList;
    }

    @Override
    public PopularMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie, parent, false);
        return new PopularMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularMovieViewHolder holder, int position) {
        final Movie movie = mPopularMovieList.get(position);
        holder.txtTenMovie.setText(movie.getTitle());
        Picasso.with(mContext).load("http://image.tmdb.org/t/p/w500/" + movie.getPosterPath()).error(R.drawable.placeholder).into(holder.imgMovie);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(DetailActivity.newIntent(mContext, movie));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPopularMovieList.size();
    }

    public static class PopularMovieViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTenMovie;
        private ImageView imgMovie;
        private CardView cardView;

        public PopularMovieViewHolder(View itemView) {
            super(itemView);
            txtTenMovie = (TextView) itemView.findViewById(R.id.txtTenMovie);
            imgMovie = (ImageView) itemView.findViewById(R.id.imgMovie);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }

}
