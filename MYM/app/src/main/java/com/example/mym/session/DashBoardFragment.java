package com.example.mym.session;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mym.R;
import com.example.mym.model.post.Post;
import com.example.mym.model.post.PostTaskLoader;
import com.example.mym.model.post.PostRCAdapter;

import java.util.ArrayList;
import java.util.List;

public class DashBoardFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Post>>{
    RecyclerView dashBoard;
    PostRCAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        dashBoard = view.findViewById(R.id.dashBoard);
        dashBoard.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new PostRCAdapter(this.getContext(),new ArrayList<Post>());
        dashBoard.setAdapter(adapter);
        this.getActivity().getSupportLoaderManager().initLoader(0,null,this).forceLoad();
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @NonNull
    @Override
    public Loader<List<Post>> onCreateLoader(int id, @Nullable Bundle args) {
        return new PostTaskLoader(this.getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Post>> loader, List<Post> data) {
        //Log.d("root",data.getUserName());
        adapter = new PostRCAdapter(this.getContext(),  data);
        dashBoard.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Post>> loader) {

    }
}
