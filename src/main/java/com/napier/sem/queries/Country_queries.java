package com.napier.sem.queries;

public class Country_queries {

    public static String query1 = "SELECT Code, country.Name, country.Continent, country.Region, country.Population, city.Name AS Capital "
            + "FROM country "
            + "JOIN city ON country.Capital=city.ID "
            + "ORDER BY Population DESC";

    public static String query2 = "SELECT Code, country.Name, country.Continent, country.Region, country.Population, city.Name AS Capital "
            + "FROM country "
            + "JOIN city ON country.Capital=city.ID "
            + "WHERE country.Continent= ";

    public static String query3 = "SELECT Code, country.Name, country.Continent, country.Region, country.Population, city.Name AS Capital "
            + "FROM country "
            + "JOIN city ON country.Capital=city.ID "
            + "WHERE country.Region= ";

}
