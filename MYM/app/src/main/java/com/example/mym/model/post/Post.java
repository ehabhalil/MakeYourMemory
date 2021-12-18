package com.example.mym.model.post;

public class Post {
    private String ID;
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
