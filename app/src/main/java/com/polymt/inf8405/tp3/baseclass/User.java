package com.polymt.inf8405.tp3.baseclass;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Louis-Philippe on 4/6/2017.
 */

public class User {
    protected String uniqueId;
    protected String name;
    protected String email;
    protected String description;
    protected Map<String,Boolean> friends;
    protected Map<String,Boolean> ownedVideos;


    public User(String uniqueId){
        this.uniqueId = uniqueId;
        //TODO remove this, its temporary
        this.name = uniqueId;
        loadInfo();
    }


    public User(String name, String email){
        this.name = name;
        this.email=email;
        friends = new HashMap<>();
        ownedVideos = new HashMap<>();

    }

    private void loadInfo() {
        //TODO Load info associated with UID
        //Name, email,descrition
    }
    public String getName(){return name;}
    public String getUniqueId(){
        return uniqueId;
    }
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
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

    public void setDescription(String description){
        this.description = description;
    }

    public Map<String, Boolean> getFriends() {
        return friends;
    }

    public void setFriends(Map<String, Boolean> friends) {
        this.friends = friends;
    }

    public Map<String, Boolean> getOwnedVideos() {
        return ownedVideos;
    }

    public void setOwnedVideos(Map<String, Boolean> ownedVideos) {
        this.ownedVideos = ownedVideos;
    }
}

