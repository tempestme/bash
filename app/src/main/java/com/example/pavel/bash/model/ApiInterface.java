package com.example.pavel.bash.model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pavel on 02.01.18.
 */

public interface ApiInterface {
    public static String BASE_URL = "http://umorili.herokuapp.com/";

    //"http://www.umori.li/api/get?site=bash.im&name=bash&num=50"

    /**
     *
     * @param name
     *             name can recieve diffirent parameters
     *             Deti for det.org.ru
     *             ideer for ideer.ru
     *             zadolbali for zadolba.li
     *             bash/abyss/random for bash.im and for "name" parametrs there is abyss/bash
     *             XKCDB for XKCDB.com
     *             new anekdot for anekdot.ru
     *             random for random quotes
     *
     * @param num
     * @return
     */
    @GET("api/get")
    Call<ArrayList<Post>> getPosts(@Query("site")String site, @Query("name")String name, @Query("num")int num);

    @GET("api/get")
    Call<ArrayList<Post>> getPosts(@Query("site")String site, @Query("num")int num);

}
