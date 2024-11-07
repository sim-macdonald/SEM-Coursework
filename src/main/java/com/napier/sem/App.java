package com.napier.sem;

import com.napier.sem.Database;
import com.napier.sem.reports.Country;
import com.napier.sem.reports.City;
import com.napier.sem.queries.*;
import com.napier.sem.reports.Population;

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

        //if N <= 0 then set no limit
        int N  = 5;

        // Connect to database
        a.connect();

        // Extract country information
        ArrayList<Country> countries = a.getCountryWorld(Country_queries.query1, N);
        // Display results
        a.printCountries(countries);

        a.getPopulationReport("Continent", "Asia");

        ArrayList<City> cities = a.getCitiesByPopulation();

        // Disconnect from database
        a.disconnect();
    }
}