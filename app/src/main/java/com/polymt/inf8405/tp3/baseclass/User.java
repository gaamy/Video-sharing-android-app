package com.polymt.inf8405.tp3.baseclass;

/**
 * Created by Louis-Philippe on 4/6/2017.
 */

public abstract class User {
    protected String uniqueId;
    public String getUniqueId(){return uniqueId;}
    protected String name;
    protected String email;
    protected String description;
    public User(String name, String email){
        this.name = name;
        this.email=email;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public User(String uniqueId){
        this.uniqueId = uniqueId;
        //TODO remove this, its temporary
        this.name = uniqueId;
        loadInfo();
    }
    private void loadInfo() {
        //TODO Load info associated with UID
        //Name, email,descrition
    }
    public String getName(){return name;}
}
