package com.napier.sem.queries;

public class Population_queries {

    // Query to get total of world
    public static String query = "SELECT 'World' AS Name, "
            + "SUM(country.Population) AS TotalPopulation, "
            + "SUM(city.Population) AS CityPopulation, "
            + "(SUM(country.Population) - SUM(city.Population)) AS NonCityPopulation, "
            + "ROUND((SUM(city.Population) / SUM(country.Population)) * 100, 2) AS CityPercentage, "
            + "ROUND(((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population)) * 100, 2) AS NonCityPercentage "
            + "FROM country "
            + "LEFT JOIN city ON country.Capital = city.ID";
}
