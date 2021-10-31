package com.example.moviedbapps.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedbapps.model.Movies;
import com.example.moviedbapps.model.NowPlaying;
import com.example.moviedbapps.model.Popular;
import com.example.moviedbapps.model.Upcoming;
import com.example.moviedbapps.repositories.MovieRepository;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MovieViewModel(@NonNull Application application){
        super(application);
        repository = MovieRepository.getInstance();
    }

    //Begin of viewmodel getMoviebyId
    private MutableLiveData<Movies> resultGetMoviesById = new MutableLiveData<>();
    public void getMovieById(String movieId){resultGetMoviesById = repository.getMovieData(movieId);}
    public LiveData<Movies> getResultGetMovieById(){return resultGetMoviesById;}
    //End of viewmodel getmoviebyID

    //Begin of viewmodel getNowPlaying
    private MutableLiveData<NowPlaying> resultGetNowPlaying = new MutableLiveData<>();
    public void getNowPlayingData(){resultGetNowPlaying = repository.getNowPlayingData();}
    public LiveData<NowPlaying> getResultGetNowPlaying(){return resultGetNowPlaying;}
    //End of viewmodel getNowPlaying

    //Begin of viewmodel getUpcoming
    private MutableLiveData<Upcoming> resultGetUpComing = new MutableLiveData<>();
    public void getUpcomingData(){resultGetUpComing = repository.getUpcomingData();}
    public LiveData<Upcoming> getResultGetUpComing(){return resultGetUpComing;}
    //End of viewmodel getUpcoming

    //Begin of viewmodel getPopular
    private MutableLiveData<Popular> resultGetPopular = new MutableLiveData<>();
    public void getPopularData(){resultGetPopular = repository.getPopularData();}
    public LiveData<Popular> getResultGetPopular(){return resultGetPopular;}
    //End of viewmodel getPopular
}
