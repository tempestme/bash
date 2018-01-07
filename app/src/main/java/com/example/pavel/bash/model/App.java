package com.example.pavel.bash.model;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pavel on 05.01.18.
 */

public class App extends Application{
    private Retrofit retrofit;
    private static Api api;

    @Override
    public void onCreate(){
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    public static Api getApi(){
        return api;
    }


}
