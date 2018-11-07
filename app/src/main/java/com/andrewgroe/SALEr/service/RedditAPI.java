package com.andrewgroe.SALEr.service;

import com.andrewgroe.SALEr.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RedditAPI {

    @Headers("Content-Type: application/json")
    @GET("{sub}/hot/.json?limit=50")
    Call<Feed> getFeed(@Path("sub") String subReddit);
}
