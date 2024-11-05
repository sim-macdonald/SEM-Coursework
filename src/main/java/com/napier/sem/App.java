package com.napier.sem;

import com.napier.sem.Database;
import com.napier.sem.reports.Country;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        Database a = new Database();

        // Connect to database
        a.connect();

        // Extract country information
        ArrayList<Country> countries = a.getCountry();
        // Display results
        a.printCountries(countries);

        // Disconnect from database
        a.disconnect();
    }
}