package com.polymt.inf8405.tp3.baseclass;

import android.graphics.Bitmap;
import android.location.Location;
import android.media.Image;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

/**
 * Created by Louis-Philippe on 4/6/2017.
 */

public class VideoInfo {
    private int uniqueId;
    private Location videoLocation;
    private byte[] rawData;
    private Date date;
    private Bitmap thumbnail;
    private String description;
    private String name;

    public boolean hasVideoData(){return rawData!=null;}
    public LatLng getCoordinate(){return new LatLng(videoLocation.getLatitude(), videoLocation.getLongitude());}
    public String getName(){return name;}
    public Bitmap getThumbnail(){return thumbnail;}
    public String getDescription(){return description;}
    public int getUniqueId(){return uniqueId;}
}
