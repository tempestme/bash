package com.example.pavel.bash.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by pavel on 02.01.18.
 */

public interface Api {
    public static String BASE_URL = "http://umorili.herokuapp.com/";

    //"http://www.umori.li/api/get?site=bash.im&name=bash&num=50"

    @GET("api/get")
    Call<List<Post>> getPosts();

}
