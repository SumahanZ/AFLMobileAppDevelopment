package com.example.moviedbapps.retrofit;

import com.example.moviedbapps.helper.Const;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {  //inisialisasi retrofit ke baseurl dan tipe data yang mau diconvert

    private static Retrofit retrofit;

    public static ApiEndPoint endpoint(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiEndPoint.class);
    }

}
