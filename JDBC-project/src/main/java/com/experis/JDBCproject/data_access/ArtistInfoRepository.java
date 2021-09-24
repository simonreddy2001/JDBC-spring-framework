package com.experis.JDBCproject.data_access;

import com.experis.JDBCproject.log.logToConsole;
import com.experis.JDBCproject.models.ArtistInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ArtistInfoRepository {
    private static final String URL = connection_helper.CONNECTION_URL;

    private static Connection conn = null;


    /**
     * Gets artist info
     *
     * @param searchTerm - search Term
     * @return everything that matches the search term.
     */
    public ArrayList<ArtistInfo> getArtistInfo(String searchTerm) {
        ArrayList<ArtistInfo> artistInfoArrayList = new ArrayList<>();
        try {
            //Connect to DB
            conn = DriverManager.getConnection(URL);

            PreparedStatement ps = conn.prepareStatement("SELECT Ar.ArtistId, Ar.Name AS Artist, Gr.Name AS Genre, Al.Title as Album, " +
                    " Tr.Name AS Track " +
                    " FROM Artist Ar " +
                    " INNER JOIN Album Al ON Al.AlbumId = Al.AlbumId " +
                    " INNER JOIN Track Tr ON Tr.TrackId= Tr.TrackId " +
                    " INNER JOIN Genre Gr ON Tr.GenreId= Gr.GenreId " +
                    " WHERE Ar.Name LIKE ?" +
                    "limit 20");

            ps.setString(1, "%" + searchTerm + "%");

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                artistInfoArrayList.add(
                        new ArtistInfo(
                                resultSet.getInt("ArtistId"),
                                resultSet.getString("Artist"),
                                resultSet.getString("Track"),
                                resultSet.getString("Album"),
                                resultSet.getString("Genre")
                        )
                );
            }
        } catch (Exception e) {
            logToConsole.log(e.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                logToConsole.log(e.toString());
            }
        }
        return artistInfoArrayList;
    }
}
