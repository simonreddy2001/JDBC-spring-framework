package com.experis.JDBCproject.models;

public class ArtistInfo {
    private final int ArtistId;
    private final String TrackName;
    private final String ArtistName;
    private final String AlbumTitle;
    private final String Genre;

    public ArtistInfo(int artistId,  String artistName, String trackName, String albumTitle, String genre) {
        ArtistId = artistId;
        ArtistName = artistName;
        TrackName = trackName;
        AlbumTitle = albumTitle;
        Genre = genre;
    }

    public int getArtistId() {
        return ArtistId;
    }

    public String getTrackName() {
        return TrackName;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public String getAlbumTitle() {
        return AlbumTitle;
    }

    public String getGenre() {
        return Genre;
    }
}
