package com.experis.JDBCproject.models;

public class Artist {
    private int ArtistId;
    private String Name;

    public Artist(int artistId, String name) {
        this.ArtistId = artistId;
        this.Name = name;
    }

    public int getArtistId() {
        return ArtistId;
    }

    public String getName() {
        return Name;
    }

    public void setArtistId(int artistId) {
        ArtistId = artistId;
    }

    public void setName(String name) {
        Name = name;
    }
}
