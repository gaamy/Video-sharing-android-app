package com.polymt.inf8405.tp3.baseclass;

import android.location.Location;
import android.media.Image;

import java.util.Date;

/**
 * Created by Louis-Philippe on 4/6/2017.
 */

public class VideoInfo {
    private int uniqueId;
    private Location videoLocation;
    private byte[] rawData;
    private Date date;
    private Image thumbnail;
    private String description;

    public boolean hasVideoData(){return rawData!=null;}
}
