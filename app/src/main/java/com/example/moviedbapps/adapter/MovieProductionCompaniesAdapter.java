package com.example.moviedbapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedbapps.R;
import com.example.moviedbapps.helper.Const;
import com.example.moviedbapps.model.Movies;
import com.example.moviedbapps.model.NowPlaying;
import com.example.moviedbapps.view.activities.MovieDetailsActivity;

import java.util.List;

public class MovieProductionCompaniesAdapter extends RecyclerView.Adapter<MovieProductionCompaniesAdapter.CardViewViewHolder>{

    private Context context;
    private List<Movies.ProductionCompanies> listPC;

    private List<Movies.ProductionCompanies> getListPC(){
        return listPC;
    }
    public void setListPC(List<Movies.ProductionCompanies> listPC){ //Dipanggil di Activity
        this.listPC = listPC;
    }

    public MovieProductionCompaniesAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MovieProductionCompaniesAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_production,parent,false);
        return new MovieProductionCompaniesAdapter.CardViewViewHolder(view);
    }

    @Override  //Setting image
    public void onBindViewHolder(@NonNull MovieProductionCompaniesAdapter.CardViewViewHolder holder, int position) {
        final Movies.ProductionCompanies results = getListPC().get(position);
            Glide.with(context).load(Const.IMG_URL + results.getLogo_path()).into(holder.img_production);

        holder.img_production.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, results.getName(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return getListPC().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_production;
        private CardView cv;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            img_production = itemView.findViewById(R.id.img_production);
            cv = itemView.findViewById(R.id.cv_movie_production);
        }
    }
}



