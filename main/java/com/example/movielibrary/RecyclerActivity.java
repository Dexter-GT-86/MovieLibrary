package com.example.movielibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        listAllMovie(receiveAllMovies());
    }

    public String receiveAllMovies(){
        return (String) getIntent().getSerializableExtra("All_Movies");
    }

    @SuppressLint("NotifyDataSetChanged")
    public void listAllMovie(String allMovies){

        adapter = new RecyclerAdapter(allMovies);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //A RecyclerView.LayoutManager  implementation which provides similar functionality to ListView.
        // Also StaggeredGridLayoutManager and GridLayoutManager or a custom Layout manager
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}