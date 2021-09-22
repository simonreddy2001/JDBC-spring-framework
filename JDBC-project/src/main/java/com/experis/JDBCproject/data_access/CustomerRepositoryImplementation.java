package com.experis.JDBCproject.data_access;

import com.experis.JDBCproject.log.logToConsole;
import com.experis.JDBCproject.models.CustomerPerCountry;
import com.experis.JDBCproject.models.CustomersMostPopularGenre;
import com.experis.JDBCproject.models.HighestSpenders;
import com.experis.JDBCproject.models.customer;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository
public class CustomerRepositoryImplementation implements CustomerRepository {
    private final logToConsole log;
    private final String URL = connection_helper.CONNECTION_URL;
    private Connection conn = null;

    public CustomerRepositoryImplementation(logToConsole log) {
        this.log = log;
    }

    @Override
    public ArrayList<customer> getAllCustomers() {
        ArrayList<customer> customers = new ArrayList<>();
        try{
            conn = DriverManager.getConnection(URL);
            logToConsole.log("connection established");
            PreparedStatement ps = conn.prepareStatement ("SELECT  CustomerId, FirstName, LastName, Email, PostalCode, Phone, Country FROM customer");

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                customers.add(
                        new customer(
                                resultSet.getString("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Country"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email")
                        )
                );
            }
            logToConsole.log("selected all customers");
        }
        catch (Exception e){
             logToConsole.log(e.toString());
        }
        finally {
            try{
                conn.close();
            }
            catch (Exception e) {
                logToConsole.log(e.toString());
            }
        }
        return customers;
    }

    @Override
    public customer getCustomerById(String customerId) {
        customer customer = null;
        try{
            conn = DriverManager.getConnection(URL);
            logToConsole.log("connection established");
            PreparedStatement ps = conn.prepareStatement ("SELECT  CustomerId, FirstName, LastName, Email, PostalCode, Phone, Country FROM customer WHERE CustomerId= ?");
            ps.setString(1,customerId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                customer= new customer(
                                resultSet.getString("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Country"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email")
                );
            }
            logToConsole.log("selected customer by Id");
        }
        catch (Exception e){
            logToConsole.log(e.toString());
        }
        finally {
            try{
                conn.close();
            }
            catch (Exception e) {
                logToConsole.log(e.toString());
            }
        }
        return customer;
    }
    /**
     * Selects customer by firstname and lastname
     *
     * @param firstName - customer firstname
     * @param lastName  - customer lastname
     * @return that specific customer info.
     */
    @Override
    public customer selectCustomerByName(String firstName, String lastName) {
        customer customer = null;
        try {
            //Connect to DB
            conn = DriverManager.getConnection(URL);
            logToConsole.log("Connection to SQLite has been established");

            //Make SQL Query
            PreparedStatement ps = conn.prepareStatement("SELECT  CustomerId, FirstName, LastName, Email,PostalCode, Phone, Country " +
                    "FROM Customer " +
                    "WHERE FirstName LIKE ? AND LastName LIKE ? ");

            ps.setString(1, "%" + firstName + "%");
            ps.setString(2, "%" + lastName + "%");

            //Execute Query
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                customer = new customer(
                        resultSet.getString("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")

                );
            }
            logToConsole.log("Selected customer by name successfully");


        } catch (Exception e) {
            logToConsole.log(e.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                logToConsole.log(e.toString());
            }
        }
        return customer;
    }


    /**
     * selects customers by limit and offset
     *
     * @param limit  - the number of customers to be displayed
     * @param offset - offset
     * @return a customer list based on limit and offset
     */
    @Override
    public ArrayList<customer> selectCustomersByOffsetAndLimit(int limit, int offset) {
        ArrayList<customer> customers = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement ps = conn.prepareStatement(" SELECT CustomerId, FirstName, LastName, Email,PostalCode, Phone, Country " +
                    "FROM Customer LIMIT ? OFFSET ?");
            ps.setInt(1, limit);
            ps.setInt(2, offset);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                customers.add(
                        new customer(
                                resultSet.getString("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Country"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email")
                        )
                );
                logToConsole.log("selectCustomerByOffsetAndLimit executed successfully!");
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
        return customers;
    }

    /**
     * Adds a new customer to the database
     *
     * @param customer
     * @return the newly added customer
     */
    @Override
    public Boolean addCustomer(customer customer) {
        boolean success = false;
        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO Customer (CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getCountry());
            preparedStatement.setString(5, customer.getPostalCode());
            preparedStatement.setString(6, customer.getPhone());
            preparedStatement.setString(7, customer.getEmail());

            int result = preparedStatement.executeUpdate();
            success = (result != 0); // if
            System.out.println("Add went well");
        } catch (Exception e) {
            e.toString();

        } finally {
            try {
                conn.close();
            } catch (Exception exception) {
                exception.toString();
            }
        }
        // ---
        return success;
    }


    /**
     * Select customer by country
     *
     * @return customers by country
     */
    @Override
    public ArrayList<CustomerPerCountry> selectCustomersByCountry() {
        ArrayList<CustomerPerCountry> customersByCountry = new ArrayList<>();
        try {
            //Connect to DB
            conn = DriverManager.getConnection(URL);
            logToConsole.log("Connection to SQLite has been established");
            //Make SQL Query
            PreparedStatement ps = conn.prepareStatement("SELECT  COUNT(CustomerId) AS Customers, Country " +
                    "FROM Customer " +
                    "GROUP BY Country " +
                    "ORDER BY COUNT(CustomerId) DESC ");

            //Execute Query
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                customersByCountry.add(
                        new CustomerPerCountry(
                                resultSet.getInt("Customers"),
                                resultSet.getString("Country")
                        )
                );
            }
            logToConsole.log("SelectCustomersByCountry run successfully");

        } catch (Exception e) {
            logToConsole.log(e.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception exception) {
                logToConsole.log(exception.toString());
            }
        }
        return customersByCountry;
    }

    /**
     * updated an existing customer
     *  @param id             - customer id
     * @param updateCustomer - the updated customer
     */
    @Override
    public void updateCustomer(String id, customer updateCustomer) {
        customer existingCustomer = getCustomerById(id);

        try {
            conn = DriverManager.getConnection(URL);
            logToConsole.log("Connection to Database has been established");

            // Make SQL Query
            PreparedStatement ps = conn.prepareStatement("UPDATE Customer " +
                    "SET FirstName=?, LastName=?, Country=?, PostalCode=?, Phone=?, Email=? " +
                    "WHERE CustomerId= ?");

            ps.setString(1, updateCustomer.getFirstName() == null || updateCustomer.getFirstName().isEmpty()
                    ? existingCustomer.getFirstName() : updateCustomer.getFirstName());

            ps.setString(2, updateCustomer.getLastName() == null || updateCustomer.getLastName().isEmpty()
                    ? existingCustomer.getLastName() : updateCustomer.getLastName());

            ps.setString(3, updateCustomer.getCountry() == null || updateCustomer.getCountry().isEmpty()
                    ? existingCustomer.getCountry() : updateCustomer.getCountry());

            ps.setString(4, updateCustomer.getPostalCode() == null || updateCustomer.getPostalCode().isEmpty()
                    ? existingCustomer.getPostalCode() : updateCustomer.getPostalCode());

            ps.setString(5, updateCustomer.getPhone() == null || updateCustomer.getPhone().isEmpty()
                    ? existingCustomer.getPhone() : updateCustomer.getPhone());

            ps.setString(6, updateCustomer.getEmail() == null || updateCustomer.getEmail().isEmpty()
                    ? existingCustomer.getEmail() : updateCustomer.getEmail());

            ps.setString(7, id);
            ps.executeUpdate();
            System.out.println("Successfully updated!");
        } catch (Exception e) {
            logToConsole.log(e.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                logToConsole.log(e.toString());
            }
        }
    }

    /**
     * select customers who paid highest
     *
     * @return highest spenders
     */
    @Override
    public ArrayList<HighestSpenders> selectHighestSpenders() {
        ArrayList<HighestSpenders> highestSpenders = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement ps = conn.prepareStatement("SELECT C.CustomerId, C.FirstName, C.LastName, SUM(TOTAL) AS Total " +
                    "FROM Customer C " +
                    "JOIN Invoice I on C.CustomerId = I.CustomerId " +
                    "GROUP BY I.CustomerId " +
                    "ORDER BY Total DESC LIMIT 10");

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                highestSpenders.add(
                        new HighestSpenders(
                                resultSet.getString("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getInt("Total")
                        )
                );
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.toString();
            }
        }
        return highestSpenders;
    }

    /**
     * Select the most popular genre of a specific customer
     *
     * @param id - customer id
     * @return info about the most poplar genre of that customer
     */
    @Override
    public ArrayList<CustomersMostPopularGenre> selectCustomersMostPopularGenre(String id) {
        ArrayList<CustomersMostPopularGenre> customersMostPopularGenre = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(URL);
            PreparedStatement ps = conn.prepareStatement("WITH CustomersMostPopularGenre AS (\n" +
                    "    SELECT Cu.FirstName, Cu.LastName, Ge.Name AS GenreType, COUNT(Ge.GenreId) AS GenreCount\n" +
                    "    FROM Customer Cu\n" +
                    "             INNER JOIN Invoice Inv ON Cu.CustomerId = Inv.CustomerId\n" +
                    "             INNER JOIN InvoiceLine Il ON Inv.InvoiceId = Il.InvoiceId\n" +
                    "             INNER JOIN Track Tr ON Tr.TrackId = Il.TrackId\n" +
                    "             INNER JOIN Genre Ge ON Ge.GenreId = Tr.GenreId\n" +
                    "    WHERE Cu.CustomerId = ?\n" +
                    "    GROUP BY Ge.GenreId)\n" +
                    "\n" +
                    "SELECT CustomersMostPopularGenre.*\n" +
                    "FROM CustomersMostPopularGenre\n" +
                    "WHERE GenreCount = (SELECT MAX(GenreCount) FROM CustomersMostPopularGenre)");

            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                customersMostPopularGenre.add(
                        new CustomersMostPopularGenre(
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("GenreType"),
                                resultSet.getInt("GenreCount")
                        )
                );
            }
        } catch (Exception e) {
            logToConsole.log(e.toString());
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                logToConsole.log(e.getMessage());
            }
        }
        return customersMostPopularGenre;
    }
}
