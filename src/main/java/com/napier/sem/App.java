package com.napier.sem;

import com.napier.sem.Database;
import com.napier.sem.reports.Country;
import com.napier.sem.queries.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        Database a = new Database();

        //Scanner for user input
        //Scanner scanner = new Scanner(System.in);

        int N  = 0;

        // Connect to database
        a.connect();

        // Extract country information
        ArrayList<Country> countries = a.getCountryWorld(Country_queries.query1, N);
        // Display results
        a.printCountries(countries);

        // Disconnect from database
        a.disconnect();
    }
}