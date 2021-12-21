package com.example.mym.session.friends_tab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mym.R;
import com.example.mym.model.user.User;
import com.example.mym.server.Constants;
import com.example.mym.server.Server;
import com.example.mym.server.UserController;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FriendsFragment extends Fragment implements LoaderManager.LoaderCallbacks<String>{
    RecyclerView friends;
    UserRCAdapter adapter;
    View view;
    static User user;
    public FriendsFragment(User user){
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_friends, container, false);
        friends = view.findViewById(R.id.friendsRecycleView);
        friends.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new UserRCAdapter(this.getContext(),new ArrayList<User>(),user,this);
        friends.setAdapter(adapter);
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
        return new Server(this.getContext(), Constants.GET_ALL_USERS, "GET",null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        UserController userController = new UserController();
        ArrayList<User> usersList = userController.getAllUsers(data);
        adapter = new UserRCAdapter(this.getContext(),  usersList,user,this);
        friends.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}