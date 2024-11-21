package com.napier.sem.queries;

/**
 * Contains predefined SQL queries for retrieving information about capital cities.
 */
public class CapitalCity_queries {
    /**
     * Query to get all capital cities in the world sorted by population.
     */
    public static final String GET_ALL_CAPITAL_CITIES_WORLD =
            "SELECT city.Name, country.Name AS Country, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "ORDER BY city.Population DESC";

    /**
     * Query to get all capital cities in a specific continent sorted by population.
     * Replace `?` with the continent name when calling the query.
     */
    public static final String GET_ALL_CAPITAL_CITIES_CONTINENT =
            "SELECT city.Name, country.Name AS Country, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "WHERE country.Continent = ? " +
                    "ORDER BY city.Population DESC";

    /**
     * Query to get all capital cities in a specific region sorted by population.
     * Replace `?` with the region name when calling the query.
     */
    public static final String GET_ALL_CAPITAL_CITIES_REGION =
            "SELECT city.Name, country.Name AS Country, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "WHERE country.Region = ? " +
                    "ORDER BY city.Population DESC";
}
