package com.experis.JDBCproject.data_access;

import com.experis.JDBCproject.models.CustomerPerCountry;
import com.experis.JDBCproject.models.CustomersMostPopularGenre;
import com.experis.JDBCproject.models.HighestSpenders;
import com.experis.JDBCproject.models.customer;
import java.util.ArrayList;

public interface CustomerRepository {
    ArrayList<customer> getAllCustomers();
    customer getCustomerById(String customerId);
    customer selectCustomerByName(String firstName, String lastName);
    ArrayList<customer> selectCustomersByOffsetAndLimit(int limit, int offset);
    Boolean addCustomer(customer customer);
    ArrayList<CustomerPerCountry> selectCustomersByCountry();
    void updateCustomer(String id, customer updateCustomer);
    ArrayList<HighestSpenders> selectHighestSpenders();
    ArrayList<CustomersMostPopularGenre> selectCustomersMostPopularGenre(String id);
}
