package com.example.mym.model.user;

import java.util.ArrayList;

public class User {
    private String userName;
    private String firstName;
    private String lastName;
    private String password_hash;
    private String registered_at;
    private String avatar;
    private ArrayList<String> friends;

    public User(String userName, String firstName, String lastName, String password_hash, String registered_at, String avatar, ArrayList<String> friends) {
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

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }
}
