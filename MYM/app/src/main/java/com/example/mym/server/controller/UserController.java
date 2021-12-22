package com.example.mym.server.controller;

import com.example.mym.server.model.user.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserController {
    // TODO: 12/14/2021 get all user

    public UserController() {

    }
    public ArrayList<User> getAllUsers(String data){
        ArrayList<User> usersList = new ArrayList<User>();
        JSONArray users = null;
        try {
            users = new JSONArray(data);
            for (int i = 0; i < users.length(); i++) {

                User p = getUser(users.getJSONObject(i),true);
                usersList.add(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return usersList;
    }
    public User getUser(JSONObject user,Boolean withoutHisFriends) throws JSONException {
        if(!withoutHisFriends)
            return
                new User(
                        user.getString("_id"),
                        user.getString("userName"),
                        user.getString("firstName"),
                        user.getString("lastName"),
                        user.getString("password_hash"),
                        user.getString("registered_at"),
                        user.getString("avatar"),
                        handleUserFriend(user.getJSONArray("friends"))
                );
        else return new User(
                user.getString("_id"),
                user.getString("userName"),
                user.getString("firstName"),
                user.getString("lastName"),
                user.getString("password_hash"),
                user.getString("registered_at"),
                user.getString("avatar"),
                null
        );
    }

    private ArrayList<User> handleUserFriend(JSONArray friends) throws JSONException {
        ArrayList<User> friendsList = new ArrayList<User>();
        for (int i = 0; i < friends.length(); i++) {
            User user = new User(
                    friends.getJSONObject(i).getString("_id"),
                    friends.getJSONObject(i).getString("userName"),
                    friends.getJSONObject(i).getString("firstName"),
                    friends.getJSONObject(i).getString("lastName"),
                    friends.getJSONObject(i).getString("password_hash"),
                    friends.getJSONObject(i).getString("registered_at"),
                    friends.getJSONObject(i).getString("avatar"),
                   null
            );
            friendsList.add(user);
        }
        return friendsList;
    }

}
