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
}
