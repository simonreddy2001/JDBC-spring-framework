package com.experis.JDBCproject.models;

public class GenreInfo {
    private final int GenreId;
    private final String TrackName;
    private final String ArtistName;
    private final String AlbumTitle;
    private final String Genre;

    public GenreInfo(int genreId, String trackName, String artistName, String albumTitle, String genre) {
        GenreId = genreId;
        TrackName = trackName;
        ArtistName = artistName;
        AlbumTitle = albumTitle;
        Genre = genre;
    }

    public int getGenreId() {
        return GenreId;
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
