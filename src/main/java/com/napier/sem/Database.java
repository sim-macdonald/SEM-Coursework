package com.napier.sem;

import com.napier.sem.queries.Country_queries;
import com.napier.sem.reports.*;
import com.napier.sem.queries.Population_queries;
import com.napier.sem.queries.Language_queries;


import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        boolean shouldWait = false;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                if (shouldWait) {
                    // Wait a bit for db to start
                    Thread.sleep(delay);
                }

                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "group20");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());

                // Let's wait before attempting to reconnect
                shouldWait = true;
            } catch (InterruptedException ie) {
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

    //Methods for creating Country Reports - Simon
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
                cou.population = rset.getLong("Population");
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
                cou.population = rset.getLong("Population");
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
                cou.population = rset.getLong("Population");
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
     * Generates a population report for a specific level of aggregation and returns a list of Population objects.
     *
     * @param level the level of aggregation (Continent, Region, or Country)
     * @param name  the name of the level (e.g., "Asia", "Europe", or a specific country)
     * @return a list of Population objects representing the aggregated population data for multiple entries
     */
    public ArrayList<Population> getPopulationReport(String level, String name) {
        String query = Population_queries.query;
        if (level.equalsIgnoreCase("Continent")) {
            query += " " + Population_queries.continent + "'" + name + "'";
        } else if (level.equalsIgnoreCase("Region")) {
            query += " " + Population_queries.region + "'" + name + "'";
        } else if (level.equalsIgnoreCase("Country")) {
            query += " " + Population_queries.country + "'" + name + "'";
        } else {
            System.out.println("Invalid level specified.");
            return null;
        }

        ArrayList<Population> populationList = new ArrayList<>();

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String countryName = rs.getString("Name");
                long totalPopulation = rs.getLong("TotalPopulation");  // Use getLong
                long cityPopulation = rs.getLong("CityPopulation");    // Use getLong
                long nonCityPopulation = rs.getLong("NonCityPopulation"); // Use getLong

                double cityPercentage = (cityPopulation / (double) totalPopulation) * 100;
                double nonCityPercentage = (nonCityPopulation / (double) totalPopulation) * 100;

                Population population = new Population(countryName, totalPopulation, cityPopulation, nonCityPopulation, cityPercentage, nonCityPercentage);
                populationList.add(population);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return populationList;
    }



    /**
     * Prints a population report for each Population object in a readable format.
     *
     * @param populationList the list of Population objects containing the report data.
     */
    public void printPopulationReport(ArrayList<Population> populationList) {
        if (populationList == null || populationList.isEmpty()) {
            System.out.println("No population data available.");
            return;
        }

        for (Population population : populationList) {
            if (population == null){ continue;}
            System.out.println("Population Report for " + population.getName());
            System.out.println("------------------------------------------------------------");
            System.out.println("Total Population: " + population.getTotalPopulation());
            System.out.println("Population in Cities: " + population.getCityPopulation() + " (" + String.format("%.2f", population.getCityPercentage()) + "%)");
            System.out.println("Population Not in Cities: " + population.getNonCityPopulation() + " (" + String.format("%.2f", population.getNonCityPercentage()) + "%)");
            System.out.println();
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
                city.population = rset.getLong("Population");
                cities.add(city);
            }
            return cities;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    //Captial City
//-------------------------------------------------------------------------------------------------------------

    public ArrayList<Capital_City> getCapitalCitiesByPopulation(String query, int limit, String... parameter) {
        ArrayList<Capital_City> capitalCities = new ArrayList<>();

        try {
            // Prepare the query
            PreparedStatement stmt = con.prepareStatement(query);

            // Bind optional parameters
            if (parameter.length > 0) {
                stmt.setString(1, parameter[0]);
            }

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next() && (limit == 0 || capitalCities.size() < limit)) {
                Capital_City city = new Capital_City();
                city.name = rs.getString("Name");
                city.Country = rs.getString("Country");
                city.population = rs.getLong("Population");

                capitalCities.add(city);
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }

        return capitalCities;
    }


public void printCapital(ArrayList<Capital_City> capital)
    {
        // Check country is not null
        if (capital == null)
        {
            System.out.println("No capital cities");
            return;
        }
        // Print header
        System.out.println(String.format("%-15s %-15s %15s", "Name","Country", "Population"));
        // Loop over all countries in the list
        for (Capital_City cap : capital)
        {
            if (cap == null)
                continue;

            String cou_string =
                    String.format("%-15s %-15s %15s",
                            cap.name, cap.Country, cap.population);
            System.out.println(cou_string);
        }
    }

    //Methods for creating Language report - Simon
    //------------------------------------------------------------------------------------------------------------------------------

    /**
     * Retrieves a list of languages and their associated populations based on
     * the provided SQL query.
     *
     * @param query The SQL query string to fetch language details from the database.
     * @return A list of `Language` objects, each containing the name and population
     *         percentage of a language, or `null` if an error occurs.
     */
    public ArrayList<Language> getLanguages(String query) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = query;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract country information
            ArrayList<Language> report = new ArrayList<Language>();
            while (rset.next()) {
                Language lang = new Language();
                lang.name = rset.getString("Language");
                lang.population = rset.getDouble("Population");

                report.add(lang);
            }
            return report;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get language details");
            return null;
        }
    }

    /**
     * Prints a formatted list of languages and their populations.
     * This method takes the list of `Language` objects and outputs each language's
     * name and population percentage.
     *
     * @param report The list of `Language` objects containing language details
     *               to be printed. If the list is `null`, a message indicating
     *               no languages are found will be printed.
     */
    public void printLanguage(ArrayList<Language> report)
    {
        // Check country is not null
        if (report == null)
        {
            System.out.println("No languages found");
            return;
        }
        // Print header
        System.out.println(String.format("%-15s %-15s", "Language", "Population"));
        // Loop over all countries in the list
        for (Language lang : report)
        {
            if (lang == null)
                continue;

            String lang_string =
                    String.format("%-15s %-15s",
                            lang.name, lang.population + "%");
            System.out.println(lang_string);
        }
    }
}
