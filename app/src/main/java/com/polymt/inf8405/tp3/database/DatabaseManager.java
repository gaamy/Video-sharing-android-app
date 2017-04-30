package com.polymt.inf8405.tp3.database;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.polymt.inf8405.tp3.baseclass.MyLocation;
import com.polymt.inf8405.tp3.baseclass.User;
import com.polymt.inf8405.tp3.baseclass.VideoInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public List<VideoInfo> getVideos(){
        return videos;
    }
    public List<User> getUsers(){
        return users;
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

        //mDatabaseUsers.addListenerForSingleValueEvent()
    }


    public void fillDatabaseForDemo(){
        //User 1
        User user = new User("Gabriel", "gabriel.amyot@gmail.com");
        User user2 = new User("test", "test@email.com");
        users.add(user);
        users.add(user2);
        createNewUser(user);
        createNewUser(user2);

        //video 1
        MyLocation loc = new MyLocation("ileBizz");
        loc.setLatitude(45.4949);
        loc.setLongitude(-73.8908);
        VideoInfo videoInfo = new VideoInfo("Ile", loc,"ceci est un video", "bob");
        createNewVideo(videoInfo);
        videos.add(videoInfo);
        //video 2
        loc.setLatitude(45.4950);
        loc.setLongitude(-73.8908);
        VideoInfo videoInfo2 = new VideoInfo("ptit videp", loc,"Mon video", "blabla");
        createNewVideo(videoInfo2);
        videos.add(videoInfo2);
        //video 3
        loc.setLatitude(45.4951);
        loc.setLongitude(-73.8905);
        VideoInfo videoInfo3 = new VideoInfo("ptit videp", loc,"Mon video", "Mroui");
        createNewVideo(videoInfo3);
        videos.add(videoInfo3);
        //video 4
        loc.setLatitude(45.4947);
        loc.setLongitude(-73.8905);
        VideoInfo videoInfo4 = new VideoInfo("ptit videp", loc,"Mon video", "en short");
        createNewVideo(videoInfo4);
        videos.add(videoInfo4);
        //video 5
        loc.setLatitude(45.4951);
        loc.setLongitude(-73.8900);
        VideoInfo videoInfo5 = new VideoInfo("ptit videp", loc,"Mon video", "ta mere");
        createNewVideo(videoInfo5);
        videos.add(videoInfo5);
        //video 6
        loc.setLatitude(45.4949);
        loc.setLongitude(-73.8901);
        VideoInfo videoInfo6 = new VideoInfo("yoo", loc,"OMG!", "user1");
        createNewVideo(videoInfo6);
        videos.add(videoInfo6);
        //video 7
        loc.setLatitude(45.4948);
        loc.setLongitude(-73.8901);
        VideoInfo videoInfo7 = new VideoInfo("ptit2", loc,"Mon video", "Louis");
        videos.add(videoInfo7);
        createNewVideo(videoInfo7);
        //video 8
        loc.setLatitude(45.4950);
        loc.setLongitude(-73.8912);
        VideoInfo videoInfo8 = new VideoInfo("clavier", loc,"Mon claveir", "Wassim");
        videos.add(videoInfo8);
        createNewVideo(videoInfo8);

    }


    public void createNewUser(User newUser){
        DatabaseReference newUserReference = mDatabaseUsers.push();
        //String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        newUser.setUniqueId(newUserReference.getKey());
        newUserReference.setValue(newUser);

        newUser.setUniqueId(UUID.randomUUID().toString());
        users.add(newUser);
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
        newVideoMetadata.setUniqueId(UUID.randomUUID().toString());
        videos.add(newVideoMetadata);
    }

    public void updateVideo(String videoId, VideoInfo newVideo){
        DatabaseReference updatedUserReference = mFirebaseDatabase.getReference().child("videos/"+newVideo.getUniqueId());
        updatedUserReference.setValue(newVideo);
    }

    public void deleteVideo(String videoId){
        DatabaseReference updatedUserReference = mFirebaseDatabase.getReference().child("videos/"+videoId);
        updatedUserReference.removeValue();
    }

    public VideoInfo gatherVideo(String videoId ){
        for (VideoInfo videoMetadata : videos){
            if (videoMetadata.getUniqueId().equals(videoId)){
                return videoMetadata;
            }
        }
        return null;
    }

    public List<VideoInfo> gatherUserVideos(String userId){
        List<VideoInfo> result = new ArrayList<>();
        for(VideoInfo video : videos) {
            if(video.getOwnerId() == userId){
                result.add(video);
            }
        }
        return result;
    }

    public User gatherUserByMail(String email){

        for (User user :users){
            if(user.getEmail().equals(email))
                return user;
        }
        return null;
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
