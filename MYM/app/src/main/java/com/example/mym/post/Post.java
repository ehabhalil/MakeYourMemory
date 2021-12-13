package com.example.mym.post;

public class Post {
    private String text;
    private String imagURL;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImagURL() {
        return imagURL;
    }

    public void setImagURL(String imagURL) {
        this.imagURL = imagURL;
    }

    public Post(String text, String imagURL) {
        this.text = text;
        this.imagURL = imagURL;
    }
}
