package com.example.mym.server.model.user;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String UserId;
    private String userName;
    private String firstName;
    private String lastName;
    private String password_hash;
    private String registered_at;
    private String avatar;
    private ArrayList<User> friends;

    public User(String userId, String userName, String firstName, String lastName, String password_hash, String registered_at, String avatar, ArrayList<User> friends) {
        this.UserId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password_hash = password_hash;
        this.registered_at = registered_at;
        this.avatar = avatar;
        this.friends = friends;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getRegistered_at() {
        return registered_at;
    }

    public void setRegistered_at(String registered_at) {
        this.registered_at = registered_at;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public boolean exists(ArrayList<User> friends) {
        for(int i =0;i<friends.size();i++){
            if(friends.get(i).getUserId().equals(this.UserId))
                return true;
        }
        return false;
    }
}
