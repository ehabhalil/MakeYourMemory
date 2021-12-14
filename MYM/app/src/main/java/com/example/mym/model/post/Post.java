package com.example.mym.model.post;

public class Post {
    // TODO: 12/14/2021 check post class

    private String text;
    private String imageURL;

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

    public Post(String text, String imageURL) {
        this.text = text;
        this.imageURL = imageURL;
    }
}
