package com.example.mym.model.post;

public class Comment {
    // TODO: 12/14/2021 set comment class
    private String text;
    private String userId;

    public Comment(String text, String userId) {
        this.text = text;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
