package com.polymt.inf8405.tp3.database.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gamyot on 2017-04-22.
 */
/*
represents something like
"user1": {
      "$user_id":{
        ".write": "$user_id == auth.uid"
      },
      "name": "Gabriel",
      "e-mail": "gabriel.amyot@gmail.com",
      "friends":{
        "user2" : true
      },
      "videos": {
        "video1": true
      },
      "unlocked_videos": {
        "video2":true
      }
    },
 */
public class DatabaseUser {

    private String userId;
    private String name;
    private String email;
    private Map<String,Boolean> friends;
    private Map<String,Boolean> videos;


    public DatabaseUser(String name, String email) {
        this.name = name;
        this.email = email;
        this.friends = new HashMap<>();
        this.videos = new HashMap<>();
    }


    public DatabaseUser(String userId, String name, String email, Map<String, Boolean> friends, Map<String, Boolean> videos) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.friends = friends;
        this.videos = videos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Boolean> getFriends() {
        return friends;
    }

    public void setFriends(Map<String, Boolean> friends) {
        this.friends = friends;
    }

    public Map<String, Boolean> getVideos() {
        return videos;
    }

    public void setVideos(Map<String, Boolean> videos) {
        this.videos = videos;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
