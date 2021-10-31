package com.example.moviedbapps.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedbapps.R;
import com.example.moviedbapps.helper.Const;
import com.example.moviedbapps.model.Movies;
import com.example.moviedbapps.retrofit.ApiEndPoint;
import com.example.moviedbapps.retrofit.ApiService;
import com.example.moviedbapps.viewmodel.MovieViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {


    private MovieViewModel viewmodel;
    private ImageView img_Poster_small;
    private TextView movie_names, movie_rating, movie_release_date, movie_vote_count, movie_overview, movie_genres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        viewmodel = new ViewModelProvider(MovieDetailsActivity.this).get(MovieViewModel.class);
        Intent intent = getIntent();
        img_Poster_small = findViewById(R.id.img_Poster_small);
        movie_names = findViewById(R.id.movie_name);
        movie_rating = findViewById(R.id.movie_rating);
        movie_release_date = findViewById(R.id.movie_release_date);
        movie_genres = findViewById(R.id.movie_genres);
        movie_vote_count = findViewById(R.id.movie_vote_count);
        movie_overview = findViewById(R.id.movie_overview);

        String id = intent.getExtras().getString("movie_id");
        if(intent != null) {
            viewmodel.getMovieById(id);
            viewmodel.getResultGetMovieById().observe(MovieDetailsActivity.this, showResultMovie);
        }
    }

//        Glide.with(MovieDetailsActivity.this).load(Const.IMG_URL + movie_image).into(img_Poster_small);
//        movie_names.setText(movie_name);
//        movie_rating.setText(movie_ratings);
//        movie_release_date.setText(movie_release_dates);
//        movie_vote_count.setText(movie_vote_counts);
//        movie_overview.setText(movie_overviews);

    @Override
    public void onBackPressed() {
        finish();
    }

    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String movie_name = "" + movies.getTitle();
            String movie_vote_counts = "" + movies.getVote_count();
            String movie_ratings = "" + movies.getVote_average();
            String movie_release_dates = "Release Date: " + movies.getRelease_date().substring(0,4);
            String movie_overviews = "" + movies.getOverview();
            String movie_imagePath = "" + movies.getBackdrop_path();
            List<Movies.Genres> movieGenres = movies.getGenres();

            Glide.with(MovieDetailsActivity.this).load(Const.IMG_URL + movie_imagePath).into(img_Poster_small);
            movie_names.setText(movie_name);
            movie_vote_count.setText(movie_vote_counts);
            movie_rating.setText(movie_ratings);
            movie_release_date.setText(movie_release_dates);
            movie_overview.setText(movie_overviews);

            if (movieGenres.size() > 0 && movieGenres != null) {
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0; i < movieGenres.size(); i++) {
                    if (i == movieGenres.size() - 1) {
                        stringBuilder.append(movieGenres.get(i).getName());
                    } else {
                        stringBuilder.append(movieGenres.get(i).getName()).append(", ");
                    }
                }
                movie_genres.setText("Genres: " + stringBuilder.toString());
            }
        }
    };

}
