package com.napier.sem;

import com.napier.sem.Database;
import com.napier.sem.reports.Capital_City;
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
        System.out.println("N set to 5");

        // Connect to database
        a.connect();

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("The top N populated countries in the world where N is provided by the user.");
        System.out.println("---------------------------------------------------------------------------");
        // Extract country information
        ArrayList<Country> countries = a.getCountryWorld(Country_queries.query1, N);
        // Display results
        a.printCountries(countries);

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("The top N populated countries in a region where N is provided by the user.");
        System.out.println("--------------------------------------------------------------------------");
        //Extract country information
        ArrayList<Country> countries2 = a.getCountryRegion(Country_queries.query1,N,"North America");
        // Display results
        a.printCountries(countries2);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("The top N populated countries in a continent where N is provided by the user.");
        System.out.println("-----------------------------------------------------------------------------");
        //Extract country information
        ArrayList<Country> countries3 = a.getCountryContinent(Country_queries.query1,N,"Asia");
        // Display results
        a.printCountries(countries3);

        ArrayList<Capital_City> cap = a.getCapitalCitiesByPopulation();
        a.printCapital(cap);

        // Disconnect from database
        a.disconnect();
    }
}