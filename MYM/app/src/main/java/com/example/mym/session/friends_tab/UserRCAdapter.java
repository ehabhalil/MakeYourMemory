package com.example.mym.session.friends_tab;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mym.R;
import com.example.mym.server.model.user.User;
import com.example.mym.server.URLs;
import com.example.mym.server.Server;
import com.example.mym.server.controller.UserController;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class UserRCAdapter extends RecyclerView.Adapter<UserRCAdapter.ViewHolder> {
    private Fragment fragment;
    Context context;
    List<User> usersList;
    User user;
    Server server;
    boolean makeFriend;
    int i = 165;
    public UserRCAdapter(Context context, List<User> usersList,User user,Fragment fragment) {
        this.context = context;
        this.usersList = usersList;
        this.user = user;
        this.fragment = fragment;
        for (int i = 0; i < usersList.size(); i++) {
            if(usersList.get(i).getUserId().equals(user.getUserId()))
                usersList.remove(i);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(usersList.get(position));
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements LoaderManager.LoaderCallbacks<String>{
        ImageView userCardAvatar;
        TextView userCardUserName;
        Button relation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userCardAvatar = itemView.findViewById(R.id.userCardAvatar);
            userCardUserName = itemView.findViewById(R.id.userCardUserName);
            relation = itemView.findViewById(R.id.relation);
            relation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(relation.getText());
                    if(relation.getText().equals("follow")){
                        makeFriend = true;
                    }
                    else {
                        makeFriend = false;
                    }
                    fragment.getActivity().getSupportLoaderManager().restartLoader(i++,null, ViewHolder.this).forceLoad();
                }
            });
        }

        public void bind(User userFriend) {
            Picasso.get().load(userFriend.getAvatar()).into(userCardAvatar);
            userCardUserName.setText(userFriend.getUserName());
            for (int i = 0; i < user.getFriends().size(); i++) {
                System.out.println(user.getFriends().get(i).getUserName());
            }
            if(userFriend.exists(user.getFriends())){
                relation.setText("unfollow");
            }
            else relation.setText("follow");
        }

        @NonNull
        @Override
        public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
            HashMap<String,String> bodyRequest = new HashMap<String, String>();
            bodyRequest.put("userId",user.getUserId());
            bodyRequest.put("friendId",usersList.get(getAdapterPosition()).getUserId());
            server = new Server(context, URLs.CHANGE_FRIEND_RELATION,"POST",bodyRequest);
            return server;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<String> loader, String data) {
            System.out.println(data);
            JSONObject jsonObject;
            if(data != null){
                try {
                    jsonObject = new JSONObject(data);
                    FriendsFragment.user = new UserController().getUser(jsonObject.getJSONObject("user"), false);
                    if(jsonObject.getBoolean("statue")) {
                        relation.setText("unfollow");
                    }
                    else {
                        relation.setText("follow");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void onLoaderReset(@NonNull Loader<String> loader) {

        }
    }
}
