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

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment implements LoaderManager.LoaderCallbacks<String>{
    RecyclerView friends;
    UserRCAdapter adapter;
    View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_friends, container, false);
        friends = view.findViewById(R.id.friendsRecycleView);
        friends.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new UserRCAdapter(this.getContext(),new ArrayList<User>());
        friends.setAdapter(adapter);
        this.getActivity().getSupportLoaderManager().initLoader(1,null,this).forceLoad();
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
        List<User> usersList = new ArrayList<User>();
        JSONArray users = null;
        try {
            users = new JSONArray(data);
            for (int i = 0; i < users.length(); i++) {

                User p = new User(
                        users.getJSONObject(i).getString("_id"),
                        users.getJSONObject(i).getString("userName"),
                        users.getJSONObject(i).getString("firstName"),
                        users.getJSONObject(i).getString("lastName"),
                        users.getJSONObject(i).getString("password_hash"),
                        users.getJSONObject(i).getString("userName"),
                        users.getJSONObject(i).getString("avatar"),null
                        //users.getJSONObject(i).getJSONArray("friends")
                );
                usersList.add(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new UserRCAdapter(this.getContext(),  usersList);
        friends.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}