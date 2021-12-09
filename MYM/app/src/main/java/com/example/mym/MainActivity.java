package com.example.mym;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mym.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<User> {
    RecyclerView recyclerView;
    RCAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RCAdapter(this,new ArrayList<Post>());
        recyclerView.setAdapter(adapter);
        getSupportLoaderManager().initLoader(0,null,this).forceLoad();
    }

    @NonNull
    @Override
    public Loader<User> onCreateLoader(int id, @Nullable Bundle args) {
        return new UserTaskLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<User> loader, User data) {
        Log.d("root",data.getUserName());
        adapter = new RCAdapter(this,  data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<User> loader) {

    }
}