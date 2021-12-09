package com.example.mym;

import java.util.ArrayList;

public class User {
    final private String userName;
    final private String firstName;
    final private String lastName;
    final private String password_hash;
    final private String registered_at;
    final private String avatar;
    final private ArrayList<User> friends;

    public User(String userName, String firstName, String lastName, String password_hash, String registered_at, String avatar, ArrayList<User> friends) {
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public String getRegistered_at() {
        return registered_at;
    }

    public String getAvatar() {
        return avatar;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

}
