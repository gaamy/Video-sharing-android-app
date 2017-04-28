package com.polymt.inf8405.tp3.database;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.polymt.inf8405.tp3.baseclass.User;
import com.polymt.inf8405.tp3.baseclass.VideoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gamyot on 2017-04-12.
 */

public class DatabaseManager {
    private static DatabaseManager mDatabaseManager;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseUsers;
    private DatabaseReference mDatabaseVideos;
    ChildEventListener userListener;
    ChildEventListener videoListener;

    private List<User> users;
    private List<VideoInfo> videos;

    public static DatabaseManager getInstance(){
        if(mDatabaseManager == null){
            mDatabaseManager = new DatabaseManager();
        }
        return mDatabaseManager;
    }
    private DatabaseManager(){
        initialize();
    }

    public void finalize(){
        //detach listeners
        mDatabaseUsers.removeEventListener(userListener);
        mDatabaseVideos.removeEventListener(videoListener);
    }

    public void initialize(){

        users  = new ArrayList<>();
        videos = new ArrayList<>();
        //initialise database references
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseUsers = mFirebaseDatabase.getReference().child("users");
        mDatabaseVideos = mFirebaseDatabase.getReference().child("videos");

        //attach listeners
        userListener = buildUserEventListener();
        mDatabaseUsers.addChildEventListener(userListener);
        videoListener = buildVideoEventListener();
        mDatabaseVideos.addChildEventListener(videoListener);
    }


    public void createNewUSer(User newUser){
        DatabaseReference newUserReference = mDatabaseUsers.push();
        //String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        newUser.setUniqueId(newUserReference.getKey());
        newUserReference.setValue(newUser);
    }

    public void updateDatabaseUser(User newUser){
        DatabaseReference updatedUserReference = mFirebaseDatabase.getReference().child("users/"+newUser.getUniqueId());
        updatedUserReference.setValue(newUser);
    }


    public User gatherUser(String userId ){
        for (User user :users){
            if (user.getUniqueId().equals(userId)){
                return user;
            }
        }
        return null;
    }
    public String findUserId(String name){
        for (User user :users){
            if (user.getName().equals(name)){
                return user.getUniqueId();
            }
        }
        return null;
    }



    public void createNewVideo(VideoInfo newVideoMetadata){
        DatabaseReference newVideoRef = mDatabaseVideos.push();
        newVideoMetadata.setUniqueId(newVideoRef.getKey());
        newVideoRef.setValue(newVideoMetadata);
    }

    public void updateVideo(String videoId, VideoInfo newVideo){
        DatabaseReference updatedUserReference = mFirebaseDatabase.getReference().child("videos/"+newVideo.getUniqueId());
        updatedUserReference.setValue(newVideo);
    }

    public VideoInfo gatherVideo(String videoId ){
        for (VideoInfo videoMetadata : videos){
            if (videoMetadata.getUniqueId().equals(videoId)){
                return videoMetadata;
            }
        }
        return null;
    }


    public void testMethod(String name, String email){

        User testUser = new User(name,email);
        //createNewUser
        createNewUSer(testUser);

        //retreiveUser
        gatherUser(testUser.getUniqueId());


        //modify user
        testUser.setName("Wassim");
        //updateDatabaseUser
        updateDatabaseUser(testUser);

        //retreive user
        gatherUser(testUser.getUniqueId());

    }


    private ChildEventListener buildUserEventListener(){
        return new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                users.add(dataSnapshot.getValue(User.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                User modifiedChild = dataSnapshot.getValue(User.class);
                modifiedChild.getUniqueId();

                User foundUser = gatherUser(modifiedChild.getUniqueId());
                if(foundUser != null){
                    users.remove(foundUser);
                }
                users.add(modifiedChild);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //users.remove(dataSnapshot.getValue(DatabaseUser.class));
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }
    private ChildEventListener buildVideoEventListener(){
        return new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                videos.add(dataSnapshot.getValue(VideoInfo.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                VideoInfo modifiedChild = dataSnapshot.getValue(VideoInfo.class);
                modifiedChild.getUniqueId();

                VideoInfo foundVideo = gatherVideo(modifiedChild.getUniqueId());
                if(foundVideo != null){
                    videos.remove(foundVideo);
                }
                videos.add(modifiedChild);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //videos.remove(dataSnapshot.getValue(VideoInfo.class));
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }





}
