package com.experis.JDBCproject.models;

public class CustomersMostPopularGenre {
    private String firstName;
    private String lastName;
    private String genreType;
    private int genreCount;

    public CustomersMostPopularGenre(String firstName, String lastName, String genreType, int genreCount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.genreType = genreType;
        this.genreCount = genreCount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGenreType() {
        return genreType;
    }

    public void setGenreType(String genreType) {
        this.genreType = genreType;
    }

    public int getGenreCount() {
        return genreCount;
    }

    public void setGenreCount(int genreCount) {
        this.genreCount = genreCount;
    }
}
