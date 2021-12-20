package com.example.mym.model.post;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Comment implements Serializable {
    // TODO: 12/14/2021 set comment class
    private String text;
    private String userName;
    private String imageUrl;

    public Comment(String text, JSONObject user) throws JSONException {
        this.text = text;
        handleUser(user);
    }

    private void handleUser(JSONObject user) throws JSONException {
        this.imageUrl = user.getString("imageURL");
        this.userName = user.getString("userName");
    }

    public Comment(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userId) {
        this.userName = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
