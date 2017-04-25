package com.polymt.inf8405.tp3.baseclass;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louis-Philippe on 4/6/2017.
 */

public class VideoManager {
    private static VideoManager m_videoManager;
    public static VideoInfo testVideoInfo;
    public static VideoManager getInstance(){
        if(m_videoManager == null){
            m_videoManager = new VideoManager();
        }
        return m_videoManager;
    }
    private VideoManager(){

    }
    public List<VideoInfo> loadMyVideo(String uId){
        //TODO load infos associatedwith my own video
        return null;
    }

    void postVideo(VideoInfo video){
        //TODO:"unimplemented method";
    }

    VideoInfo gatherVideoData(String videoId ){
        //TODO:"unimplemented method";
        return null;
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
