package com.example.mym.session.profile_tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mym.R;
import com.example.mym.server.model.post.Post;
import com.example.mym.server.model.user.User;
import com.example.mym.server.URLs;
import com.example.mym.server.controller.PostController;
import com.example.mym.server.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfileFragment extends Fragment implements LoaderManager.LoaderCallbacks<String>{
    RecyclerView posts;
    ProfileRCAdapter adapter;
    View view;
    static User user;
    public ProfileFragment(User user){
        ProfileFragment.user = user;
    }

    public void setUser(User user) {
        ProfileFragment.user = user;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_profile, container, false);
        ImageView avatar = view.findViewById(R.id.avatarP);
        TextView userName = view.findViewById(R.id.userNameP);
        userName.setText(user.getUserName());
        Picasso.get()
                .load(user.getAvatar())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(avatar);
        posts = view.findViewById(R.id.userPostsP);
        posts.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new ProfileRCAdapter(this.getContext(),new ArrayList<Post>(),user,this);
        posts.setAdapter(adapter);
        this.requireActivity().getSupportLoaderManager().restartLoader(1,null,this).forceLoad();
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        HashMap<String,String> bodyRequest = new HashMap<>();
        bodyRequest.put("userId",user.getUserId());
        return new Server(this.getContext(), URLs.GET_USER_POSTS, "POST",bodyRequest);
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
        adapter = new ProfileRCAdapter(this.getContext(),  postsList, user,this);
        posts.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}