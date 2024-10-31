package com.napier.sem;

import com.napier.sem.reports.Country;

import java.sql.*;

public class Database {

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "group20");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }

    }

    public Country getCountry(String code) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, Population, Capital "
                            + "FROM country "
                            + "WHERE code > " + code;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new country if valid.
            // Check one is returned
            if (rset.next()) {
                Country country = new Country();
                country.name = rset.getString("name");
                country.population = rset.getInt("population");
                country.capital = rset.getString("capital");
                return country;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public void displayCountry(Country country)
    {
        if (country != null)
        {
            System.out.println(
                    country.code + "\n"
                            + country.name + "\n"
                            + country.continent+ "\n"
                            + country.region + "\n"
                            + country.population + "\n"
                            + country.capital + "\n");
        }
    }

}
