package com.napier.sem.queries;

//Query strings that are used in Database methods to generate country reports

public class City_queries {

    //Default string for country report
    public static String query1 = "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population"
            + "FROM city "
            + "JOIN country ON city.ID = country.Name";

    //WHERE statements
    public static String continent = "WHERE country.Continent=";

    public static String region = "WHERE country.Region=";

    public static String country = "WHERE country.Name=";

    public static String district = "WHERE city.District=";
}
