package com.polymt.inf8405.tp3.baseclass;

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
    public void loadMyVideo(String name){
        //TODO load infos associatedwith my own video
    }
}
