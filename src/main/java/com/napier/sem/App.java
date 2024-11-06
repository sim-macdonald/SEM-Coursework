package com.napier.sem;

import com.napier.sem.Database;
import com.napier.sem.reports.Country;
import com.napier.sem.queries.*;

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

        //The WHERE clause in a sql query. null => no WHERE
        String scope = null;

        // Extract country information
        ArrayList<Country> countries = a.getCountry2(Country_queries.query1, scope, 5);
        // Display results
        a.printCountries(countries);

        // Disconnect from database
        a.disconnect();
    }
}