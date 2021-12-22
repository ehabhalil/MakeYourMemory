package com.example.mym.server.model.post;

import java.io.Serializable;

public class Like implements Serializable {
    // TODO: 12/14/2021 set like class
    private String userId;

    public Like(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
