package com.experis.JDBCproject.models;

public class HighestSpenders {
    private final String CustomerId;
    private final String FirstName;
    private final String LastName;
    private final int Total;

    public HighestSpenders(String customerId, String firstName, String lastName, int total) {
        this.CustomerId = customerId;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Total = total;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public int getTotal() {
        return Total;
    }
}
