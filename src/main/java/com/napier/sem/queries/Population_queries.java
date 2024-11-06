package com.napier.sem.queries;

public class Population_queries {

    // Default query to get population details for country, region, and continent
    public static String query = "SELECT country.Name, country.Population, city.Population AS CityPopulation "
            + "FROM country "
            + "LEFT JOIN city ON country.Capital = city.ID";

    // WHERE clauses for continent, region, or country
    public static String continent = "WHERE country.Continent=";
    public static String region = "WHERE country.Region=";
    public static String country = "WHERE country.Name=";
}
