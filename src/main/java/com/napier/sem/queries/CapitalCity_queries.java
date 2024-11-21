package com.napier.sem.queries;

/**
 * Contains predefined SQL queries for retrieving information about capital cities.
 */
public class CapitalCity_queries {
    /**
     * Query to get all capital cities in the world sorted by population.
     */
    public static final String query1 =
            "SELECT city.Name, country.Name AS Country, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "ORDER BY city.Population DESC";

    /**
     * Query to get all capital cities in a specific continent sorted by population.
     * Replace `?` with the continent name when calling the query.
     */
    public static final String query2 =
            "SELECT city.Name, country.Name AS Country, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "WHERE country.Continent = ? " +
                    "ORDER BY city.Population DESC";

    /**
     * Query to get all capital cities in a specific region sorted by population.
     * Replace `?` with the region name when calling the query.
     */
    public static final String query3 =
            "SELECT city.Name, country.Name AS Country, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "WHERE country.Region = ? " +
                    "ORDER BY city.Population DESC";

    /**
     * Query to get the top N populated capital cities in the world.
     * Replace `?` with the number of cities (N) when calling the query.
     */
    public static final String query4 =
            "SELECT city.Name, country.Name AS Country, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "ORDER BY city.Population DESC " +
                    "LIMIT ?";

    /**
     * Query to get the top N populated capital cities in a specific continent.
     * Replace `?` with the continent name and the number of cities (N) when calling the query.
     */
    public static final String query5 =
            "SELECT city.Name, country.Name AS Country, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "WHERE country.Continent = ? " +
                    "ORDER BY city.Population DESC " +
                    "LIMIT ?";

    /**
     * Query to get the top N populated capital cities in a specific region.
     * Replace `?` with the region name and the number of cities (N) when calling the query.
     */
    public static final String query6 =
            "SELECT city.Name, country.Name AS Country, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "WHERE country.Region = ? " +
                    "ORDER BY city.Population DESC " +
                    "LIMIT ?";
}
