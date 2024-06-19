package com.example.movielibrary.provider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielibrary.R;

import java.util.List;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.DatabaseViewHolder> {
    private List<MovieDB> data;

//    public DatabaseAdapter() {
//    }

    @NonNull
    @Override
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.database_item_layout, parent, false);
        return new DatabaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolder holder, int position) {
        holder.movieTitleDBTv.setText(data.get(position).getTitle());
        holder.movieCountryDBTv.setText(data.get(position).getCountry());
        holder.movieYearDBTv.setText(String.valueOf(data.get(position).getYear()));
        holder.movieGenreDBTv.setText(data.get(position).getGenre());
        holder.movieCostDBTv.setText(String.valueOf(data.get(position).getCost()));
        holder.movieKeywordsDBTv.setText(data.get(position).getKeyword());
    }

    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        else
            return data.size();
    }

    public void setMovies(List<MovieDB> newMovieDb) {
        data = newMovieDb;
    }

    public static class DatabaseViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitleDBTv;
        TextView movieYearDBTv;
        TextView movieCountryDBTv;
        TextView movieGenreDBTv;
        TextView movieCostDBTv;
        TextView movieKeywordsDBTv;

        public DatabaseViewHolder(@NonNull View itemView) {
            super(itemView);
            movieCountryDBTv = itemView.findViewById(R.id.movie_country);
            movieTitleDBTv = itemView.findViewById(R.id.movie_name);
            movieYearDBTv = itemView.findViewById(R.id.movie_year);
            movieGenreDBTv = itemView.findViewById(R.id.movie_genre);
            movieCostDBTv = itemView.findViewById(R.id.movie_cost);
            movieKeywordsDBTv = itemView.findViewById(R.id.movie_keywords);
        }
    }
}
