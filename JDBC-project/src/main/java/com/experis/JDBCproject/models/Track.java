package com.experis.JDBCproject.models;

public class Track {
    private int TrackId;
    private String Name;

    public Track(int trackId, String name) {
        this.TrackId = trackId;
        this.Name = name;
    }

    public int getTrackId() {
        return TrackId;
    }

    public String getName() {
        return Name;
    }

    public void setTrackId(int trackId) {
        TrackId = trackId;
    }

    public void setName(String name) {
        Name = name;
    }
}
