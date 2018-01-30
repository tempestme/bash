package com.example.pavel.bash.model;

import android.util.Log;

import com.example.pavel.bash.controller.ApiAdaper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pavel on 29.01.18.
 */

public class Api {

    private Call<ArrayList<Post>> call;

    public Api(String site, String name) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface api = retrofit.create(ApiInterface.class);
        this.call = api.getPosts(site, name, 100);
    }

    public Api(String site) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface api = retrofit.create(ApiInterface.class);
        this.call = api.getPosts(site, 100);
    }




    public void getPosts(final ArrayList<Post> posts, final ApiAdaper adapter){
        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                //Toast.makeText(getApplicationContext(),"successfull",Toast.LENGTH_SHORT).show();

                posts.clear();
                posts.addAll(response.body());
                adapter.notifyDataSetChanged();
                Log.e("api", "api successfull response");

            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                //Toast.makeText(getApplicationContext(),"check internet connection", Toast.LENGTH_SHORT).show();
                Log.e("api response failure",t.toString());
            }
        });
    }

}
