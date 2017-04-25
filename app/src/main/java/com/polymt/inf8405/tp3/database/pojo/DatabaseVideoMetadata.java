package com.polymt.inf8405.tp3.database.pojo;

/**
 * Created by gamyot on 2017-04-25.
 */

public class DatabaseVideoMetadata {

    private String videoId;
    private String ownerUserId;
    private String description;
    private String longitude;
    private String latitude;
    private String accesLevel;
    private String date;
    private String dataUrl;

    public DatabaseVideoMetadata(String videoId, String ownerUserId, String description, String longitude, String latitude, String accesLevel, String date, String dataUrl) {
        this.videoId = videoId;
        this.ownerUserId = ownerUserId;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.accesLevel = accesLevel;
        this.date = date;
        this.dataUrl = dataUrl;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAccesLevel() {
        return accesLevel;
    }

    public void setAccesLevel(String accesLevel) {
        this.accesLevel = accesLevel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }


}
