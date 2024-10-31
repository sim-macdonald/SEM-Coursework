package com.napier.sem;

import com.napier.sem.Database;
import com.napier.sem.reports.Country;

import java.sql.*;

public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        Database a = new Database();

        // Connect to database
        a.connect();

        // Get country
        Country country = a.getCountry("'Asia'");
        // Display results
        a.displayCountry(country);

        // Disconnect from database
        a.disconnect();
    }
}