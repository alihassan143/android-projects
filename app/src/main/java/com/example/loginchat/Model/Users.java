package com.example.loginchat.Model;

import java.util.PriorityQueue;

public class Users {
    private String username;
    private String id;
    private String imageUrl;

    public Users(String username, String id, String imageUrl) {
        this.username = username;
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
