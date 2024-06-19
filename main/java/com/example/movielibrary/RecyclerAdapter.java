package com.example.movielibrary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    ArrayList<Movie> movies = new ArrayList<>();

    public RecyclerAdapter(String movies){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Movie>>() {}.getType();
        this.movies = gson.fromJson(movies, type);

    }
    @NonNull
    @Override
    //onCreateViewHolder(): This method creates and returns a ViewHolder object initialized
    // with the view that is to be used to display the data. This view is typically created by inflating the XML layout file.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    //onBindViewHolder(): This method passes the ViewHolder object created by the onCreateViewHolder()
    // method and an integer value indicating the list item that is about to be displayed. Contained within the
    // ViewHolder object is the layout assigned by the onCreateViewHolder() method. The onBindViewHolder()
    // method is responsible for populating the views in the layout with the text and graphics corresponding
    // to the specified item and returning the object to the RecyclerView, where it will be presented to the user.
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title_display.setText(movies.get(position).getTitle());
        holder.year_display.setText(movies.get(position).getYear());
        holder.country_display.setText(movies.get(position).getCountry());
        holder.genre_display.setText(movies.get(position).getGenre());
        holder.cost_display.setText(movies.get(position).getCost());
        holder.keywords_display.setText(movies.get(position).getKeyword());
        holder.rating_display.setText(movies.get(position).getRating());
        holder.new_cost_display.setText(Integer.parseInt(movies.get(position).getCost())*0.75+"");
    }



    @Override

   // getItemCount(): This method must return a count of the number of items that are to be displayed in the list.

    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title_display;
        public TextView year_display;
        public TextView country_display;
        public TextView genre_display;
        public TextView cost_display;
        public TextView keywords_display;
        public TextView rating_display;
        public TextView new_cost_display;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_display = itemView.findViewById(R.id.title_display);
            year_display = itemView.findViewById(R.id.year_display);
            country_display = itemView.findViewById(R.id.country_display);
            genre_display = itemView.findViewById(R.id.genre_display);
            cost_display = itemView.findViewById(R.id.cost_display);
            keywords_display = itemView.findViewById(R.id.keywords_display);
            rating_display = itemView.findViewById(R.id.rating_display);
            new_cost_display = itemView.findViewById(R.id.new_cost_display);

        }
    }
}
