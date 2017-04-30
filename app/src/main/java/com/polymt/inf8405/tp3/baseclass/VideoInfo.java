package com.polymt.inf8405.tp3.baseclass;

import android.graphics.Bitmap;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

/**
 * Created by Louis-Philippe on 4/6/2017.
 */

public class VideoInfo {
    private String uniqueId;
    private MyLocation videoLocation;
    private byte[] data;
    private Date date;
    private Bitmap thumbnail;
    private String description;
    private String name;
    private String nameOfPoster;
    private String ownerId;


    public VideoInfo(){}
    public boolean hasVideoData(){return data !=null;}
    public LatLng getCoordinate(){return new LatLng(videoLocation.getLatitude(), videoLocation.getLongitude());}
    public String getName(){return name;}
    public Bitmap getThumbnail(){return thumbnail;}
    public String getDescription(){return description;}
    public String getUniqueId(){return uniqueId;}
    public void setVideoData(){

    }

    @Override
    public String toString(){
        return name+" Posted by "+ nameOfPoster;
    }

    public VideoInfo(String name,MyLocation location,String description,String nameOfPoster){
        this.name = name;
        this.videoLocation= location;
        this.description = description;
        this.nameOfPoster = nameOfPoster;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Location getVideoLocation() {
        return videoLocation;
    }

    public void setVideoLocation(MyLocation videoLocation) {
        this.videoLocation = videoLocation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOfPoster() {
        return nameOfPoster;
    }

    public void setNameOfPoster(String nameOfPoster) {
        this.nameOfPoster = nameOfPoster;
    }

    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}

