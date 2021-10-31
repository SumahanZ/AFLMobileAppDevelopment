package com.example.moviedbapps.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedbapps.R;
import com.example.moviedbapps.helper.Const;
import com.example.moviedbapps.model.Movies;
import com.example.moviedbapps.viewmodel.MovieViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel viewmodel;
    private Button btn_hit;
    private TextView txt_show;
    private TextInputLayout til_movie_id;
    private ImageView img_poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewmodel = new ViewModelProvider(MainActivity.this).get(MovieViewModel.class);
        btn_hit = findViewById(R.id.btn_hit_main);
        til_movie_id = findViewById(R.id.til_movie_id);
        img_poster = findViewById(R.id.movie_poster);
        txt_show = findViewById(R.id.txt_show_main);
        btn_hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieId = til_movie_id.getEditText().getText().toString().trim();
                if(movieId.isEmpty()){
                    til_movie_id.setError("Please fill movie id field");
                }else{
                    til_movie_id.setError(null);
                    viewmodel.getMovieById(movieId);
                    viewmodel.getResultGetMovieById().observe(MainActivity.this,showResultMovie);
                }
            }
        });
    }

    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            if(movies == null){
                txt_show.setText("Movie ID is not found");
            }else{
                String title = movies.getTitle();
                String img_path = movies.getPoster_path().toString();
                String full_path = Const.IMG_URL + img_path;
                Glide.with(MainActivity.this).load(full_path).into(img_poster);
                txt_show.setText(title);
            }
        }
    };

}