package com.experis.JDBCproject.data_access;

import com.experis.JDBCproject.log.logToConsole;
import com.experis.JDBCproject.models.GenreInfo;
import com.experis.JDBCproject.models.TrackInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GenreInfoRepository {
    private static final String URL = connection_helper.CONNECTION_URL;

    private static Connection conn = null;


    /**
     * Gets genre info
     *
     * @param searchTerm - search Term
     * @return everything that matches the search term.
     */
    public ArrayList<GenreInfo> getGenreInfo(String searchTerm) {
        ArrayList<GenreInfo> genreInfoArrayList = new ArrayList<>();
        try {
            //Connect to DB
            conn = DriverManager.getConnection(URL);

            PreparedStatement ps = conn.prepareStatement("SELECT Gr.GenreId, Gr.Name AS Genre, Ar.Name AS Artist, Al.Title as Album, " +
                    " Tr.Name AS Track " +
                    " FROM Genre Gr " +
                    " INNER JOIN Track Tr ON Tr.TrackId= Tr.TrackId " +
                    " INNER JOIN Album Al ON Tr.AlbumId = Al.AlbumId " +
                    " INNER JOIN Artist Ar ON Al.ArtistId= Ar.ArtistId " +
                    " WHERE Gr.Name LIKE ?");

            ps.setString(1, "%" + searchTerm + "%");

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                genreInfoArrayList.add(
                        new GenreInfo(
                                resultSet.getInt("GenreId"),
                                resultSet.getString("Track"),
                                resultSet.getString("Artist"),
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
        return genreInfoArrayList;
    }
}
