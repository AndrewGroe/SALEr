package com.andrewgroe.SALEr.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RedditService {

    private Retrofit retrofit = null;

    public RedditAPI getAPI() {
        String BASE_URL = "https://www.reddit.com/r/";

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(RedditAPI.class);
    }
}
