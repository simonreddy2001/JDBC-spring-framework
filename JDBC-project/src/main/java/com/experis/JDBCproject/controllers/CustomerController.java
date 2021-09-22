package com.experis.JDBCproject.controllers;

import com.experis.JDBCproject.data_access.CustomerRepository;
import com.experis.JDBCproject.models.CustomerPerCountry;
import com.experis.JDBCproject.models.CustomersMostPopularGenre;
import com.experis.JDBCproject.models.HighestSpenders;
import com.experis.JDBCproject.models.customer;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(value = "/api/customers", method = RequestMethod.GET)
    public ArrayList<customer> getAllCustomers(){
        return customerRepository.getAllCustomers();
    }

    @RequestMapping(value = "/api/customers/{id}", method = RequestMethod.GET)
    public customer getCustomerById(@PathVariable String id) {
        return customerRepository.getCustomerById(id);
    }

    /**
     * Read specific customer by first- and lastname
     *
     * @param firstName- customer firstname
     * @param lastName   - customer lastname
     * @return returns that specific customer information.
     */
    @GetMapping("api/customers/customer")
    public customer getCustomerByName(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        return customerRepository.selectCustomerByName(firstName, lastName);
    }

    /**
     * Read the customers in the database with limit and offset.
     *
     * @param limit  - limits the number of customers that are returned
     * @param offset - points out where to start.
     * @return returns a page customers from the database.
     */
    @RequestMapping(value = "/api/customers/customer-limit-and-offset")
    public ArrayList<customer> getCustomerByLimitAndOffset(@RequestParam(value = "limit") int limit, @RequestParam(value = "offset") int offset) {
        return customerRepository.selectCustomersByOffsetAndLimit(limit, offset);
    }


    /**
     * Adds a new customer to the database.
     *
     * @param customer - the new customer adding.
     */

    @RequestMapping(value = "/api/customer/", method = RequestMethod.POST)
    public boolean addCustomer(@RequestBody customer customer) {
        return customerRepository.addCustomer(customer);
    }


    /**
     * Updates an existing customer
     *
     * @param id       -customer id
     * @param customer - customer
     */
    @RequestMapping(value = "/api/customers/update-customer/{id}", method = RequestMethod.PUT)
    public void updateCustomer(@PathVariable String id, @RequestBody customer customer) {
        customerRepository.updateCustomer(id, customer);
    }

    /**
     * Read customers per country.
     *
     * @return the number of customers in each country.
     */
    @RequestMapping(value = "/api/customers/customer-per-countries", method = RequestMethod.GET)
    public ArrayList<CustomerPerCountry> getCustomerByCountry() {
        return customerRepository.selectCustomersByCountry();
    }

    /**
     * Read customers who are the highest spenders.
     *
     * @return returns the total each customer spent
     */
    @RequestMapping(value = "/api/customers/highest-spenders", method = RequestMethod.GET)
    public ArrayList<HighestSpenders> getHighestSpenders() {
        return customerRepository.selectHighestSpenders();
    }

    /**
     * Read a given customers most popular genre
     *
     * @param id - customer id
     * @return - the firstname, lastname, genreType and GenreCount of that specific customer.
     */
    @RequestMapping(value = "/api/customers/most-popular-genre/{id}", method = RequestMethod.GET)
    public ArrayList<CustomersMostPopularGenre> getCustomersMostPopularGenre(@PathVariable String id) {
        return customerRepository.selectCustomersMostPopularGenre(id);
    }


}
