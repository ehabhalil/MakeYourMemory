package com.example.mym.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mym.post.Post;
import com.example.mym.R;
import com.example.mym.post.RCAdapter;
import com.example.mym.post.PostTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class DashBoardActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Post>> {
    RecyclerView dashBoard;
    RCAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dash_board);
        dashBoard = findViewById(R.id.dashBoard);
        dashBoard.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RCAdapter(this,new ArrayList<Post>());
        dashBoard.setAdapter(adapter);
        getSupportLoaderManager().initLoader(0,null,this).forceLoad();
    }

    @NonNull
    @Override
    public Loader<List<Post>> onCreateLoader(int id, @Nullable Bundle args) {
        return new PostTaskLoader(DashBoardActivity.this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Post>> loader, List<Post> data) {
        //Log.d("root",data.getUserName());
        adapter = new RCAdapter(this,  data);
        dashBoard.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Post>> loader) {

    }
}