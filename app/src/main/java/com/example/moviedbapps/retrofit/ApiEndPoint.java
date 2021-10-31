package com.example.moviedbapps.retrofit;

import com.example.moviedbapps.model.Movies;
import com.example.moviedbapps.model.NowPlaying;
import com.example.moviedbapps.model.Popular;
import com.example.moviedbapps.model.Upcoming;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndPoint {  //routing

    @GET("movie/{movie_id}") //data yang diterima
    Call<Movies> getMovieById(
            @Path("movie_id") String movieId, //movie/{movieid}
            @Query("api_key") String apiKey  //?apikey=...
    );

    @GET("movie/now_playing") //data yang diterima
    Call<NowPlaying> getNowPlaying( //api key for authorizing
            @Query("api_key") String apiKey //?apikey=...
    );

    @GET("movie/upcoming") //data yang diterima
    Call<Upcoming> getUpcoming(
            @Query("api_key") String apiKey //?apikey=...
    );

    @GET("movie/popular")
    Call<Popular> getPopular(
            @Query("api_key") String apiKey
    );


}
