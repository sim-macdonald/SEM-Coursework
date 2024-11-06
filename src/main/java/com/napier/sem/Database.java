package com.napier.sem;

import com.napier.sem.queries.Country_queries;
import com.napier.sem.reports.City;
import com.napier.sem.reports.Country;
import com.napier.sem.reports.Population;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

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

    //Methods for creating Country Reports
//--------------------------------------------------------------------------------------------------

    /**
     * Retrieves a list of countries in the world based on a given query.
     * Optionally limits the number of countries returned.
     *
     * @param query the SQL query to execute to fetch country data, found in Country_queries.
     * @param N the maximum number of countries to return. If N <= 0, no limit is applied.
     * @return a list of countries matching the query or null if an error occurs.
     */
    public ArrayList<Country> getCountryWorld(String query, int N) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = query + " ORDER BY Population DESC";

            //if N <= 0 then set no limit
            if(N>0) {
                strSelect = strSelect + " LIMIT " + N;
            }

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract country information
            ArrayList<Country> country = new ArrayList<Country>();
            while (rset.next()) {
                Country cou = new Country();
                cou.code = rset.getString("Code");
                cou.name = rset.getString("Name");
                cou.continent = rset.getString("Continent");
                cou.region = rset.getString("Region");
                cou.population = rset.getInt("Population");
                cou.capital = rset.getString("Capital");
                country.add(cou);
            }
            return country;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    /**
     * Retrieves a list of countries in a specific region, based on a given query.
     * Optionally limits the number of countries returned.
     *
     * @param query the SQL query to execute to fetch country data, found in Country_queries.
     * @param N the maximum number of countries to return. If N <= 0, no limit is applied.
     * @param region the region to filter the countries by.
     * @return a list of countries in the specified region or null if an error occurs.
     */
    public ArrayList<Country> getCountryRegion(String query, int N, String region) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = query + " " + Country_queries.region + "'"+region+"'" + " ORDER BY Population DESC";

            //if N <= 0 then set no limit
            if(N>0) {
                strSelect = strSelect + " LIMIT " + N;
            }

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract country information
            ArrayList<Country> country = new ArrayList<Country>();
            while (rset.next()) {
                Country cou = new Country();
                cou.code = rset.getString("Code");
                cou.name = rset.getString("Name");
                cou.continent = rset.getString("Continent");
                cou.region = rset.getString("Region");
                cou.population = rset.getInt("Population");
                cou.capital = rset.getString("Capital");
                country.add(cou);
            }
            return country;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    /**
     * Retrieves a list of countries in a specific continent, based on a given query.
     * Optionally limits the number of countries returned.
     *
     * @param query the SQL query to execute to fetch country data, found in Country_queries.
     * @param N the maximum number of countries to return. If N <= 0, no limit is applied.
     * @param continent the continent to filter the countries by.
     * @return a list of countries in the specified continent or null if an error occurs.
     */
    public ArrayList<Country> getCountryContinent(String query, int N, String continent) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = query + " " + Country_queries.continent + "'"+continent+"'" + " ORDER BY Population DESC";

            //if N <= 0 then set no limit
            if(N>0) {
                strSelect = strSelect + " LIMIT " + N;
            }

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract country information
            ArrayList<Country> country = new ArrayList<Country>();
            while (rset.next()) {
                Country cou = new Country();
                cou.code = rset.getString("Code");
                cou.name = rset.getString("Name");
                cou.continent = rset.getString("Continent");
                cou.region = rset.getString("Region");
                cou.population = rset.getInt("Population");
                cou.capital = rset.getString("Capital");
                country.add(cou);
            }
            return country;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    /**
     * Prints the details of a list of countries in a formatted table.
     * Each country's details (code, name, continent, region, population, and capital) are printed on a separate line.
     *
     * @param country the list of countries to print.
     */
    public void printCountries(ArrayList<Country> country)
    {
        // Check country is not null
        if (country == null)
        {
            System.out.println("No countries");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-40s %-15s %-25s %-15s %15s", "Code","Name", "Continent", "Region", "Population","Capital"));
        // Loop over all countries in the list
        for (Country cou : country)
        {
            if (cou == null)
                continue;

            String cou_string =
                    String.format("%-10s %-40s %-15s %-25s %-15s %15s",
                            cou.code, cou.name, cou.continent, cou.region, cou.population, cou.capital);
            System.out.println(cou_string);
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------

    public void getPopulationReport(String level, String name) {
        // Define the queries to get total population and city population
        String totalPopulationQuery = "";
        String cityPopulationQuery = "";

        // Adjust query based on the level (Continent, Region, or Country)
        switch (level.toLowerCase()) {
            case "continent":
                totalPopulationQuery = "SELECT SUM(Population) FROM country WHERE Continent = '" + name + "'";
                cityPopulationQuery = "SELECT SUM(Population) FROM city WHERE CountryCode IN (SELECT Code FROM country WHERE Continent = '" + name + "')";
                break;
            case "region":
                totalPopulationQuery = "SELECT SUM(Population) FROM country WHERE Region = '" + name + "'";
                cityPopulationQuery = "SELECT SUM(Population) FROM city WHERE CountryCode IN (SELECT Code FROM country WHERE Region = '" + name + "')";
                break;
            case "country":
                totalPopulationQuery = "SELECT Population FROM country WHERE Name = '" + name + "'";
                cityPopulationQuery = "SELECT SUM(Population) FROM city WHERE CountryCode IN (SELECT Code FROM country WHERE Name = '" + name + "')";
                break;
            default:
                System.out.println("Invalid level: " + level);
                return;
        }

        try {
            // Execute the total population query
            Statement stmt = con.createStatement();
            ResultSet rsetTotalPopulation = stmt.executeQuery(totalPopulationQuery);
            long totalPopulation = 0;

            if (rsetTotalPopulation.next()) {
                totalPopulation = rsetTotalPopulation.getInt(1);
            }

            // Execute the city population query
            ResultSet rsetCityPopulation = stmt.executeQuery(cityPopulationQuery);
            long cityPopulation = 0;

            if (rsetCityPopulation.next()) {
                cityPopulation = rsetCityPopulation.getInt(1);
            }

            // Calculate the population not living in cities
            long nonCityPopulation = totalPopulation - cityPopulation;

            // Calculate the percentage of people living in cities and not living in cities
            long cityPercentage = totalPopulation > 0 ? (long) cityPopulation / totalPopulation * 100 : 0;
            long nonCityPercentage = totalPopulation > 0 ? (long) nonCityPopulation / totalPopulation * 100 : 0;

            // Print the report
            System.out.println("Population Report for " + name + " (" + level + ")");
            System.out.println("------------------------------------------------------------");
            System.out.println("Total Population: " + totalPopulation);
            System.out.println("Population in Cities: " + cityPopulation + " (" + String.format("%.2f", cityPercentage) + "%)");
            System.out.println("Population Not in Cities: " + nonCityPopulation + " (" + String.format("%.2f", nonCityPercentage) + "%)");

        } catch (SQLException e) {
            System.out.println("Error in getting population report: " + e.getMessage());
        }
    }

    public void printPopulation(ArrayList<Country> countries) {
        // Check if the countries list is null
        if (countries == null) {
            System.out.println("No countries provided.");
            return;
        }

        // Check if the list is empty
        if (countries.isEmpty()) {
            System.out.println("No countries in the list.");
            return;
        }

        // Print the header for the population report
        System.out.println(String.format("%-10s %-40s %-15s %-25s %-15s %15s", "Code", "Name", "Continent", "Region", "Population", "Capital"));

        // Loop through each country in the list
        for (Country cou : countries) {
            // Skip null countries in the list
            if (cou == null) {
                continue;
            }

            // Check if the population is a valid value
            if (cou.population < 0) {
                System.out.println("Error: Country " + cou.name + " has an invalid negative population value.");
                continue;
            }

            // Handle the case where population is zero
            if (cou.population == 0) {
                System.out.println(String.format("%-10s %-40s %-15s %-25s %-15s %15s", cou.code, cou.name, cou.continent, cou.region, "0", cou.capital));
                continue;
            }

            // Print the country details
            System.out.println(String.format("%-10s %-40s %-15s %-25s %-15d %15s", cou.code, cou.name, cou.continent, cou.region, cou.population, cou.capital));
        }
    }

    public ArrayList<City> getCitiesByPopulation() {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT ID, Name, CountryCode, District, Population FROM city ORDER BY Population DESC";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> cities = new ArrayList<>();
            while (rset.next()) {
                City city = new City();
                city.ID = rset.getInt("ID");
                city.name = rset.getString("Name");
                city.countryCode = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                cities.add(city);
            }
            return cities;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }


    //------------------------------------------------------------------------------------------------------------------------------

}
