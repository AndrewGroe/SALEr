package com.andrewgroe.SALEr.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;

import com.andrewgroe.SALEr.R;
import com.andrewgroe.SALEr.model.children.Children;
import com.andrewgroe.SALEr.presenter.RedditPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements RedditView {

    ProgressDialog progressDialog;
    String currentSub;
    RedditPresenter redditPresenter;
    ArrayList<Children> postsList = new ArrayList<>();
    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init Bottom NavBar & Listeners
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setOnNavigationItemReselectedListener(mOnNavigationItemReselectedListener);

        // Init Toolbar
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        // Init RecyclerView / Adapter
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, postsList);
        recyclerView.setAdapter(adapter);

        // Init Presenter
        redditPresenter = new RedditPresenter(this);

        // Check if data was saved (Activity Restarted / Orientation Change)
        if (savedInstanceState != null) {
            // Restore Data (No Network Call)
            postsList = (ArrayList<Children>) savedInstanceState.getSerializable("posts_state");
            currentSub = savedInstanceState.getString("current_sub_state");
            updateUI(postsList);
        } else {
            // Fetch Data (API)
            currentSub = "buildapcsales";
            initProgress();
            redditPresenter.getPosts(currentSub);

        }
    }

    // Show Progress Dialog
    private void initProgress() {
        // Set up progress before call
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("loading....");
        progressDialog.setTitle("r/" + currentSub);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        // show it
        progressDialog.show();
    }

    // Retrofit Response Returned
    @Override
    public void postsReady(ArrayList<Children> posts) {
        updateUI(posts);
        postsList.clear();
        postsList.addAll(posts);
    }

    // Populate RecyclerView & Set ActionBar Title
    private void updateUI(ArrayList<Children> posts) {
        adapter = new Adapter(this, posts);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        getSupportActionBar().setTitle("r/" + currentSub);
    }

    // Save data (Activity Restarted / Orientation Change)
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("posts_state", postsList);
        outState.putString("current_sub_state", currentSub);
    }

    // Listen For Bottom NavBar Selection
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_parts:
                    currentSub = "buildapcsales";
                    initProgress();
                    redditPresenter.getPosts(currentSub);
                    return true;
                case R.id.navigation_games:
                    currentSub = "gamedeals";
                    initProgress();
                    redditPresenter.getPosts(currentSub);
                    return true;
                case R.id.navigation_laptops:
                    currentSub = "laptopdeals";
                    initProgress();
                    redditPresenter.getPosts(currentSub);
                    return true;
            }
            return false;
        }
    };

    // Listen For Bottom NavBar Reselection
    // Avoid Re-fetching Data on Reselection
    private BottomNavigationView.OnNavigationItemReselectedListener mOnNavigationItemReselectedListener
            = new BottomNavigationView.OnNavigationItemReselectedListener() {

        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {
        }
    };
}
