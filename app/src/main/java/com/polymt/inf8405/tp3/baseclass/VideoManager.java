package com.polymt.inf8405.tp3.baseclass;

import android.location.Location;

import com.polymt.inf8405.tp3.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louis-Philippe on 4/6/2017.
 */

public class VideoManager {
    private static VideoManager m_videoManager;
    private DatabaseManager databaseManager;
    //private MediaStorageManager mediaStorageManager;
    public static VideoInfo testVideoInfo;

    public static VideoManager getInstance(){
        if(m_videoManager == null){
            m_videoManager = new VideoManager();
        }
        return m_videoManager;
    }
    private VideoManager(){
        databaseManager = DatabaseManager.getInstance();
        //mediaStorageManager = MediaStorageManager.getInstance();
    }
    public List<VideoInfo> loadMyVideo(){
        if(Me.getMe()!=null) {
            String uid = Me.getMe().uniqueId;
            return databaseManager.gatherUserVideos(uid);
        }
        return new ArrayList<>();
    }

    public void deleteMyVideo(String uniqueId){
       databaseManager.deleteVideo(uniqueId);
    }

    void postVideo(VideoInfo video){
        databaseManager.createNewVideo(video);
        //mediaStorageManager.uploadVideoData(video.getUniqueId(),video.getData());

    }

    VideoInfo gatherVideoData(String videoId ){
        return databaseManager.gatherVideo(videoId);
    }

    public List<VideoInfo> findVideoSurrounding(Location position, int radius){
        String userId = Me.getMe().getUniqueId();
        String privacy = "NoClue";//TODO wth is the name
        return findVideoSurrounding(position,radius,userId,privacy);
    }
    private List<VideoInfo> findVideoSurrounding(Location position, int radius, String userId, String privacy){

        ArrayList<VideoInfo> vi = new ArrayList<>();

        //TODO:"unimplemented method";
        return vi;
    }
}
