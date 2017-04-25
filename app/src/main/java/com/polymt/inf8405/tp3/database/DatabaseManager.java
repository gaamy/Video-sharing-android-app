package com.polymt.inf8405.tp3.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.polymt.inf8405.tp3.database.pojo.DatabaseUser;

/**
 * Created by gamyot on 2017-04-12.
 */

public class DatabaseManager {



    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseUsers;
    private DatabaseReference mDatabaseVideos;


    public void initialize(){
        //initialise database references
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseUsers = mFirebaseDatabase.getReference().child("users");
        mDatabaseVideos = mFirebaseDatabase.getReference().child("videos");
        // attach listeners
    }

    public void createNewUSer(String name, String email){
        DatabaseUser newUser = new DatabaseUser(name,email);
        DatabaseReference newUserReference = mDatabaseUsers.push();
        newUser.setUserId(newUserReference.getKey());
        newUserReference.setValue(newUser);
    }


    void testMethod(){

    }



    void updateUser(){}

    void updateVideo(){}
    void pushVideo(){}






}
