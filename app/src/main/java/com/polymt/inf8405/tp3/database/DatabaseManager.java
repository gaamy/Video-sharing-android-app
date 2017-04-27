package com.polymt.inf8405.tp3.database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.polymt.inf8405.tp3.database.pojo.DatabaseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gamyot on 2017-04-12.
 */

public class DatabaseManager {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseUsers;
    private DatabaseReference mDatabaseVideos;


    public DatabaseManager(){
        initialize();
    }

    public void initialize(){
        //initialise database references
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseUsers = mFirebaseDatabase.getReference().child("users");
        mDatabaseVideos = mFirebaseDatabase.getReference().child("videos");
        // attach listeners
    }

    public void createNewUSer(DatabaseUser newUser){
        DatabaseReference newUserReference = mDatabaseUsers.push();
        newUser.setUserId(newUserReference.getKey());
        newUserReference.setValue(newUser);
    }

    public void updateDatabaseUser(String userId, DatabaseUser newUser){
        DatabaseReference updatedUserReference = mFirebaseDatabase.getReference().child("users/"+newUser.getUserId());
        updatedUserReference.setValue(newUser);
    }



    public List<DatabaseUser> gatherUser(String userId){
        final List<DatabaseUser> results = new ArrayList<>();
        DatabaseReference wantedUserRef = mFirebaseDatabase.getReference().child("users/"+userId);
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DatabaseUser retrievedUser = dataSnapshot.getValue(DatabaseUser.class);
                results.add(retrievedUser);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        wantedUserRef.addListenerForSingleValueEvent(userListener);
        return results;
    }



    public void testMethod(String name, String email){

        System.out.printf("dbug");

        DatabaseUser testUser = new DatabaseUser(name,email);
        //createNewUser
        createNewUSer(testUser);

        //retreiveUser
        List<DatabaseUser> users1 = gatherUser(testUser.getUserId());

        if(users1.contains(testUser)){
            int i=0;
        }

        //modify user
        testUser.setName("Wassim");
        //updateDatabaseUser
        updateDatabaseUser(testUser.getUserId(), testUser);

        //retreive user
        List<DatabaseUser> users2 = gatherUser(testUser.getUserId());

        if(users1.contains(testUser)){
            int i=0;
        }
    }






}
