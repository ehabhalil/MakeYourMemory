package com.example.mym.model.post;

import java.util.ArrayList;

public class Post {
    private String ID;
    private String text;
    private String imageURL;
    private ArrayList<Like> likes;
    private ArrayList<Comment> comments;

    public Post(String ID, String text, String imageURL, ArrayList<Like> likes, ArrayList<Comment> comments) {
        this.ID = ID;
        this.text = text;
        this.imageURL = imageURL;
        this.likes = likes;
        this.comments = comments;
    }
    public Post(String ID, String text, String imageURL) {
        this.ID = ID;
        this.text = text;
        this.imageURL = imageURL;
    }

    public ArrayList<Like> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Like> likes) {
        this.likes = likes;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imagURL) {
        this.imageURL = imagURL;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
