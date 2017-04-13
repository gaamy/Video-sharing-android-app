package com.polymt.inf8405.tp3.BaseClass;

/**
 * Created by Louis-Philippe on 4/6/2017.
 */

public class VideoManager {
    private VideoManager m_videoManager;
    public VideoManager getInstance(){
        if(m_videoManager == null){
            m_videoManager = new VideoManager();
        }
        return m_videoManager;
    }
    private VideoManager(){

    }
}
