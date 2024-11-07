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

    /**
     * Retrieves a population report for a specified level (Continent, Region, Country).
     *
     * @param level the level of aggregation (Continent, Region, or Country)
     * @param name  the name of the level (e.g., "Asia", "Europe", or a specific country)
     * @return a Population object containing the population data, or null if an error occurs.
     */
    public Population getPopulationReport(String level, String name) {
        String totalPopulationQuery;
        String cityPopulationQuery;

        // Define queries based on level
        switch (level.toLowerCase()) {
            case "continent":
                totalPopulationQuery = "SELECT SUM(Population) FROM country WHERE Continent = ?";
                cityPopulationQuery = "SELECT SUM(Population) FROM city WHERE CountryCode IN (SELECT Code FROM country WHERE Continent = ?)";
                break;
            case "region":
                totalPopulationQuery = "SELECT SUM(Population) FROM country WHERE Region = ?";
                cityPopulationQuery = "SELECT SUM(Population) FROM city WHERE CountryCode IN (SELECT Code FROM country WHERE Region = ?)";
                break;
            case "country":
                totalPopulationQuery = "SELECT Population FROM country WHERE Name = ?";
                cityPopulationQuery = "SELECT SUM(Population) FROM city WHERE CountryCode = (SELECT Code FROM country WHERE Name = ?)";
                break;
            default:
                System.out.println("Invalid level: " + level);
                return null;
        }

        try (PreparedStatement stmtTotal = con.prepareStatement(totalPopulationQuery);
             PreparedStatement stmtCity = con.prepareStatement(cityPopulationQuery)) {

            // Set the parameters for the queries
            stmtTotal.setString(1, name);
            stmtCity.setString(1, name);

            // Execute and retrieve total population
            ResultSet rsetTotal = stmtTotal.executeQuery();
            long totalPopulation = rsetTotal.next() ? rsetTotal.getLong(1) : 0;

            // Execute and retrieve city population
            ResultSet rsetCity = stmtCity.executeQuery();
            long cityPopulation = rsetCity.next() ? rsetCity.getLong(1) : 0;

            // Calculate non-city population and percentages
            long nonCityPopulation = totalPopulation - cityPopulation;
            double cityPercentage = totalPopulation > 0 ? (double) cityPopulation / totalPopulation * 100 : 0;
            double nonCityPercentage = totalPopulation > 0 ? (double) nonCityPopulation / totalPopulation * 100 : 0;

            // Return the population data in a Population object
            return new Population(name, totalPopulation, cityPopulation, nonCityPopulation, cityPercentage, nonCityPercentage);

        } catch (SQLException e) {
            System.out.println("Error in getting population report: " + e.getMessage());
            return null;
        }
    }

    /**
     * Prints a population report in a readable format.
     *
     * @param population the Population object containing the report data.
     */
    public void printPopulationReport(Population population) {
        if (population == null) {
            System.out.println("No population data available.");
            return;
        }

        System.out.println("Population Report for " + population.getName());
        System.out.println("------------------------------------------------------------");
        System.out.println("Total Population: " + population.getTotalPopulation());
        System.out.println("Population in Cities: " + population.getCityPopulation() + " (" + String.format("%.2f", population.getCityPercentage()) + "%)");
        System.out.println("Population Not in Cities: " + population.getNonCityPopulation() + " (" + String.format("%.2f", population.getNonCityPercentage()) + "%)");
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
