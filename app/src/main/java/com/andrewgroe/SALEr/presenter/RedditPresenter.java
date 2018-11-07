package com.andrewgroe.SALEr.presenter;

import android.util.Log;

import com.andrewgroe.SALEr.model.Feed;
import com.andrewgroe.SALEr.model.children.Children;
import com.andrewgroe.SALEr.service.RedditService;
import com.andrewgroe.SALEr.view.RedditView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RedditPresenter {

    private static final String TAG = "RedditPresenter";


    private RedditView redditView;
    private RedditService redditService;

    public RedditPresenter(RedditView view) {
        this.redditView = view;

        if (this.redditService == null) {
            this.redditService = new RedditService();
        }
    }

    // Retrofit Request
    public void getPosts(String subReddit) {
        redditService
                .getAPI()
                .getFeed(subReddit)
                .enqueue(new Callback<Feed>() {
                    @Override
                    public void onResponse(Call<Feed> call, Response<Feed> response) {
                        Log.d(TAG, "onResponse: Server Response: " + response.toString());
                        Log.d(TAG, "onResponse: feed: " + response.body().toString());

                        ArrayList<Children> childrenList = response.body().getData().getChildren();
                        redditView.postsReady(childrenList);
                    }

                    @Override
                    public void onFailure(Call<Feed> call, Throwable t) {
                        Log.e(TAG, "onFailure: unable to retrieve Json: " + t.getMessage());
                    }
                });
    }
}
