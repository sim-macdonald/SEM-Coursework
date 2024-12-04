package com.napier.sem;

import com.napier.sem.Database;
import com.napier.sem.reports.*;
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

        //if N <= 0 then set no limit
        int N  = 5;
        System.out.println("N set to 5");

        // Connect to database
        if (args.length < 1) {
            a.connect("localhost:33060", 10000);
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }

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

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("The number of people who speak the following the following languages: Chinese, English, Hindi, Spanish, Arabic");
        System.out.println("-----------------------------------------------------------------------------");
        //Extract country information
        ArrayList<Language> languageReport = a.getLanguages(Language_queries.query);
        // Display results
        a.printLanguage(languageReport);

// Retrieve and print reports for capital cities
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("All the capital cities in the world organized by largest population to smallest.");
        System.out.println("-----------------------------------------------------------------------------");
// capital cities in the world
        ArrayList<Capital_City> capitalCitiesWorld = a.getCapitalCitiesByPopulation(CapitalCity_queries.query1, 0);
        a.printCapital(capitalCitiesWorld);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("All the capital cities in a continent organized by largest population to smallest.");
        System.out.println("-----------------------------------------------------------------------------");
//  capital cities in a continent
        ArrayList<Capital_City> capitalCitiesContinent = a.getCapitalCitiesByPopulation(CapitalCity_queries.query2, 0);
        a.printCapital(capitalCitiesContinent);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("All the capital cities in a region organized by largest to smallest.");
        System.out.println("-----------------------------------------------------------------------------");
// capital cities in a region
        ArrayList<Capital_City> capitalCitiesRegion = a.getCapitalCitiesByPopulation(CapitalCity_queries.query3, 0);
        a.printCapital(capitalCitiesRegion);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("The top N populated capital cities in the world where N is provided by the user.");
        System.out.println("-----------------------------------------------------------------------------");
// top N capital cities in the world
        ArrayList<Capital_City> topNCapitalCitiesWorld = a.getCapitalCitiesByPopulation(CapitalCity_queries.query4, N);
        a.printCapital(topNCapitalCitiesWorld);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("The top N populated capital cities in a continent where N is provided by the user.");
        System.out.println("-----------------------------------------------------------------------------");
// top N capital cities in a continent
        ArrayList<Capital_City> topNCapitalCitiesContinent = a.getCapitalCitiesByPopulation(CapitalCity_queries.query5, N);
        a.printCapital(topNCapitalCitiesContinent);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("The top N populated capital cities in a region where N is provided by the user.");
        System.out.println("-----------------------------------------------------------------------------");
// top N capital cities in a region
        ArrayList<Capital_City> topNCapitalCitiesRegion = a.getCapitalCitiesByPopulation(CapitalCity_queries.query6, N);
        a.printCapital(topNCapitalCitiesRegion);

        // Disconnect from database
        a.disconnect();


    }
}