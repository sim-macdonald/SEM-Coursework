package com.napier.sem.queries;

public class Population_queries {

    //Default string for population report
    public static String query1 = "SELECT Code, city.Population, country.Population, city.Name AS Capital "
            + "FROM population "
            + "JOIN city ON country.Capital=city.ID";

    //WHERE statements
    public static String continent = "WHERE country.Continent=";

    public static String region = "WHERE country.Region=";

}
