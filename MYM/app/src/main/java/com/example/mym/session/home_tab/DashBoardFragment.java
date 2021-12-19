package com.example.mym.session.home_tab;

import android.os.Bundle;
import android.util.Log;
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
import com.example.mym.model.user.User;
import com.example.mym.server.Constants;
import com.example.mym.server.PostController;
import com.example.mym.server.Server;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DashBoardFragment extends Fragment implements LoaderManager.LoaderCallbacks<String>{
    RecyclerView dashBoard;
    PostRCAdapter adapter;
    View view;
    User user;
    public DashBoardFragment(User user) {
        this.user = user;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_main, container, false);
        dashBoard = view.findViewById(R.id.dashBoard);
        dashBoard.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new PostRCAdapter(this.getContext(),new ArrayList<Post>(), user,this);
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
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new Server(this.getContext(), Constants.GET_ALL_POSTS, "GET",null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        PostController postController = new PostController();
        List<Post> postsList = new ArrayList<Post>();
        try {
            postsList = postController.getAllPost(new JSONArray(data));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new PostRCAdapter(this.getContext(),  postsList, user,this);
        dashBoard.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
