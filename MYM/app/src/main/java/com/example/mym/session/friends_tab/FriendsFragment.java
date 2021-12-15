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

import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<User>>{
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
    public Loader<List<User>> onCreateLoader(int id, @Nullable Bundle args) {
        return new UserTaskLoader(this.getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<User>> loader, List<User> data) {
        adapter = new UserRCAdapter(this.getContext(),  data);
        friends.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<User>> loader) {

    }
}