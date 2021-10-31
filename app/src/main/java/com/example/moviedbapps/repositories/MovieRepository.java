package com.example.moviedbapps.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.moviedbapps.helper.Const;
import com.example.moviedbapps.model.Movies;
import com.example.moviedbapps.model.NowPlaying;
import com.example.moviedbapps.model.Popular;
import com.example.moviedbapps.model.Upcoming;
import com.example.moviedbapps.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static MovieRepository repository;



    public static MovieRepository getInstance() {
        if (repository == null) {
            repository = new MovieRepository();
        }
        return repository;
    }

    public MutableLiveData<Movies> getMovieData(String movieId) {
        final MutableLiveData<Movies> result = new MutableLiveData<>();

        //ambil data dari API (movieId dan API_key) //lemparan path dan query dari interface
        ApiService.endpoint().getMovieById(movieId, Const.API_KEY).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                result.setValue(response.body()); //Set value dari MutableLiveData
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {}
        });
        return result;
    }

    public MutableLiveData<NowPlaying> getNowPlayingData() {
        final MutableLiveData<NowPlaying> result = new MutableLiveData<>();
        //ambil data dari API (movieId dan API_key)

        // lemparan path dan query dari interface
        ApiService.endpoint().getNowPlaying(Const.API_KEY).enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                result.setValue(response.body()); //Set Value MutableLiveData
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {}
        });
        return result;
    }

    public MutableLiveData<Upcoming> getUpcomingData() {
        final MutableLiveData<Upcoming> result = new MutableLiveData<>();
        //ambil data dari API (movieId dan API_key)

        // lemparan path dan query dari interface
        ApiService.endpoint().getUpcoming(Const.API_KEY).enqueue(new Callback<Upcoming>() {
            @Override
            public void onResponse(Call<Upcoming> call, Response<Upcoming> response) {
                result.setValue(response.body()); //Set Value MutableLiveData
            }

            @Override
            public void onFailure(Call<Upcoming> call, Throwable t) {}
        });
        return result;
    }

    public MutableLiveData<Popular> getPopularData() {
        final MutableLiveData<Popular> result = new MutableLiveData<>();
        //ambil data dari API (movieId dan API_key)

        // lemparan path dan query dari interface
        ApiService.endpoint().getPopular(Const.API_KEY).enqueue(new Callback<Popular>() {
            @Override
            public void onResponse(Call<Popular> call, Response<Popular> response) {
                result.setValue(response.body()); //Set Value MutableLiveData
            }

            @Override
            public void onFailure(Call<Popular> call, Throwable t) {}
        });
        return result;
    }

}
