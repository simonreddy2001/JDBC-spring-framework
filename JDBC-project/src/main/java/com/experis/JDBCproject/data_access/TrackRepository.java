package com.experis.JDBCproject.data_access;

import com.experis.JDBCproject.log.logToConsole;
import com.experis.JDBCproject.models.Track;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

public class TrackRepository {
    private static final String URL = connection_helper.CONNECTION_URL;

    private static Connection conn = null;

    /**
     * Get all tracks
     *
     * @return all tracks
     */

    public ArrayList<Track> getAllTracks() {
        ArrayList<Track> tracks = new ArrayList<>();
        try {
            //Connect to DB
            conn = DriverManager.getConnection(URL);

            //Make SQL Query
            PreparedStatement ps = conn.prepareStatement("SELECT TrackId, Name FROM Track ");

            //Execute Query
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                tracks.add(
                        new Track(
                                resultSet.getInt("TrackId"),
                                resultSet.getString("Name")
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
        return tracks;
    }

    /**
     * get 5 random tracks
     *
     * @return 5 random tracks
     */

    public ArrayList<Track> getRandomTracks() {
        ArrayList<Track> randomlyGeneratedList = new ArrayList<>();
        ArrayList<Track> tracks= new ArrayList<>(getAllTracks());
        Collections.shuffle(tracks);

        for (int i = 0; i < 5; i++) {
            randomlyGeneratedList.add(tracks.get(i));
        }
        return randomlyGeneratedList;
    }

}
