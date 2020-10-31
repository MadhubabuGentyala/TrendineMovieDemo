package com.laxmi.trendinemoviedemo.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "https://api.themoviedb.org/3/trending/movie/";
    static String API_KEY = "54f3fb955ac98e731fa7efc6f1c0c0d6";

    /**
     * The return type is important here
     * The class structure that you've defined in Call<T>
     * should exactly match with your json response
     * If you are not using another api, and using the same as mine
     * then no need to worry, but if you have your own API, make sure
     * you change the return type appropriately
     * week?api_key=54f3fb955ac98e731fa7efc6f1c0c0d6
     **/
    @GET("week")
    Call<MovieList> getTrendingMovies(@Query("api_key") String api_key);

//
//    @GET("movie/week/")
//    Call<MovieList> getWeekTrendingMovies(@Query("api_key") String api_key);

}