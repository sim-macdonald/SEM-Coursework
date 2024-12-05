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
        System.out.println("All the countries in the world organised by largest population to smallest.");
        System.out.println("---------------------------------------------------------------------------");
        // Extract country information
        ArrayList<Country> countries = a.getCountryWorld(Country_queries.query1, 0);
        // Display results
        a.printCountries(countries);

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("All the countries in a region organised by largest population to smallest.");
        System.out.println("--------------------------------------------------------------------------");
        //Extract country information
        ArrayList<Country> countries2 = a.getCountryRegion(Country_queries.query1,0,"North America");
        // Display results
        a.printCountries(countries2);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("All the countries in a continent organised by largest population to smallest.");
        System.out.println("-----------------------------------------------------------------------------");
        //Extract country information
        ArrayList<Country> countries3 = a.getCountryContinent(Country_queries.query1,0,"Asia");
        // Display results
        a.printCountries(countries3);

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("The top N populated countries in the world where N is provided by the user.");
        System.out.println("N = " + N);
        System.out.println("---------------------------------------------------------------------------");
        // Extract country information
        ArrayList<Country> countries4 = a.getCountryWorld(Country_queries.query1, N);
        // Display results
        a.printCountries(countries4);

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("The top N populated countries in a region where N is provided by the user.");
        System.out.println("N = " + N);
        System.out.println("--------------------------------------------------------------------------");
        //Extract country information
        ArrayList<Country> countries5 = a.getCountryRegion(Country_queries.query1,N,"North America");
        // Display results
        a.printCountries(countries5);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("The top N populated countries in a continent where N is provided by the user.");
        System.out.println("N = " + N);
        System.out.println("-----------------------------------------------------------------------------");
        //Extract country information
        ArrayList<Country> countries6 = a.getCountryContinent(Country_queries.query1,N,"Asia");
        // Display results
        a.printCountries(countries6);

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
// capital cities in a continent
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

        ArrayList<Population> population1 = a.getPopulationReport(Population_queries.query1);
        a.printPopulationReport(population1);
        ArrayList<Population> population2 = a.getPopulationReport(Population_queries.query2);
        a.printPopulationReport(population2);
        ArrayList<Population> population3 = a.getPopulationReport(Population_queries.query3);
        a.printPopulationReport(population3);
        ArrayList<Population> population4 = a.getPopulationReport(Population_queries.query4);
        a.printPopulationReport(population4);
        ArrayList<Population> population5 = a.getPopulationReport(Population_queries.query5);
        a.printPopulationReport(population5);


        //Cities


        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("I want to be able to generate the top N populated cities in the world where N is provided by the user.");
        System.out.println("-----------------------------------------------------------------------------");
        //Extract city information
        ArrayList<City> cities = a.getCitiesByPopulation(City_queries.query1, 5);
        // Display results
        a.printCities(cities);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("I want to be able to generate all the cities in the world organised by largest population to smallest.");
        System.out.println("-----------------------------------------------------------------------------");
        //Extract city information
        ArrayList<City> cities2 = a.getCitiesByPopulation(City_queries.query1, 0);
        // Display results
        a.printCities(cities2);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("I want to be able to generate the top N populated cities in a district where N is provided by the user.");
        System.out.println("-----------------------------------------------------------------------------");
        //Extract city information
        ArrayList<City> cities3 = a.getCityDistrict(City_queries.query1, 5,"Queensland");
        // Display results
        a.printCities(cities3);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("I want to be able to generate all the cities in a district organised by largest population to smallest.");
        System.out.println("-----------------------------------------------------------------------------");
        //Extract city information
        ArrayList<City> cities4 = a.getCityDistrict(City_queries.query1, 0,"Queensland");
        // Display results
        a.printCities(cities4);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("I want to be able to generate the top N populated cities in a region where N is provided by the user.");
        System.out.println("-----------------------------------------------------------------------------");
        //Extract city information
        ArrayList<City> cities5 = a.getCityRegion(City_queries.query1, 5,"Caribbean");
        // Display results
        a.printCities(cities5);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("I want to be able to generate all the cities in a region organised by largest population to smallest.");
        System.out.println("-----------------------------------------------------------------------------");
        //Extract city information
        ArrayList<City> cities6 = a.getCityRegion(City_queries.query1, 0,"Caribbean");
        // Display results
        a.printCities(cities6);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("I want to be able to generate the top N populated cities in a continent where N is provided by the user.");
        System.out.println("-----------------------------------------------------------------------------");
        //Extract city information
        ArrayList<City> cities7 = a.getCityContinent(City_queries.query1, 5,"Asia");
        // Display results
        a.printCities(cities7);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("I want to be able to generate all the cities in a continent organised by largest population to smallest.");
        System.out.println("-----------------------------------------------------------------------------");
        //Extract city information
        ArrayList<City> cities8 = a.getCityContinent(City_queries.query1, 0,"Asia");
        // Display results
        a.printCities(cities8);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("I want to be able to generate the top N populated cities in a country where N is provided by the user.");
        System.out.println("-----------------------------------------------------------------------------");
        //Extract city information
        ArrayList<City> cities9 = a.getCityCountry(City_queries.query1, 5,"United States");
        // Display results
        a.printCities(cities9);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("I want to be able to generate all the cities in a country organised by largest population to smallest.");
        System.out.println("-----------------------------------------------------------------------------");
        //Extract city information
        ArrayList<City> cities10 = a.getCityCountry(City_queries.query1, 0,"United States");
        // Display results
        a.printCities(cities10);


        // Disconnect from database
        a.disconnect();
    }
}