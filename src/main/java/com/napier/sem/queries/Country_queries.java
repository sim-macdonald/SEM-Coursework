package com.napier.sem.queries;

public class Country_queries {

    //Default string for country report
    public static String query1 = "SELECT Code, country.Name, country.Continent, country.Region, country.Population, city.Name AS Capital "
            + "FROM country "
            + "JOIN city ON country.Capital=city.ID";

    //WHERE statements
    public static String continent = "WHERE country.Continent=";

    public static String region = "WHERE country.Region=";

}
