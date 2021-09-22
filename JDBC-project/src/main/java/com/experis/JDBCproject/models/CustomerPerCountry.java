package com.experis.JDBCproject.models;

public class CustomerPerCountry {
    private int Customers;
    private String Country;

    public CustomerPerCountry(int customers, String country) {
        Customers = customers;
        Country = country;
    }

    public int getCustomers() {
        return Customers;
    }

    public void setCustomers(int customers) {
        Customers = customers;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
