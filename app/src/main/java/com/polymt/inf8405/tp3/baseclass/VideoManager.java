package com.polymt.inf8405.tp3.baseclass;

import android.location.Location;

import java.util.List;

/**
 * Created by Louis-Philippe on 4/6/2017.
 */

public class VideoManager {
    private static VideoManager m_videoManager;
    public static VideoManager getInstance(){
        if(m_videoManager == null){
            m_videoManager = new VideoManager();
        }
        return m_videoManager;
    }
    private VideoManager(){

    }
    public void loadMyVideo(String uId){
        //TODO load infos associatedwith my own video
    }

    public List<VideoInfo> getVideoInRange(Location position,int range){
        return null;
    }
}
