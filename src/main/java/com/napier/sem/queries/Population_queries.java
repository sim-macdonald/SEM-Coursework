package com.napier.sem.queries;

public class Population_queries {

    // Query to get total of world
    public static String query1 = "SELECT 'World' AS Name, "
            + "SUM(country.Population) AS TotalPopulation, "
            + "SUM(city.Population) AS CityPopulation, "
            + "(SUM(country.Population) - SUM(city.Population)) AS NonCityPopulation, "
            + "ROUND((SUM(city.Population) / SUM(country.Population)) * 100, 2) AS CityPercentage, "
            + "ROUND(((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population)) * 100, 2) AS NonCityPercentage "
            + "FROM country "
            + "LEFT JOIN city ON country.Capital = city.ID";

    // Query to get a specific continent
    public static String query2 = "SELECT country.Continent AS Name, "
            + "SUM(country.Population) AS TotalPopulation, "
            + "SUM(city.Population) AS CityPopulation, "
            + "(SUM(country.Population) - SUM(city.Population)) AS NonCityPopulation, "
            + "ROUND((SUM(city.Population) / SUM(country.Population)) * 100, 2) AS CityPercentage, "
            + "ROUND(((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population)) * 100, 2) AS NonCityPercentage "
            + "FROM country "
            + "LEFT JOIN city ON country.Capital = city.ID "
            + "WHERE country.Continent = 'Asia' "
            + "GROUP BY country.Continent";

    // Query to get population data for a specific region
    public static String query3 = "SELECT country.Region AS Name, "
            + "SUM(country.Population) AS TotalPopulation, "
            + "SUM(city.Population) AS CityPopulation, "
            + "(SUM(country.Population) - SUM(city.Population)) AS NonCityPopulation, "
            + "ROUND((SUM(city.Population) / SUM(country.Population)) * 100, 2) AS CityPercentage, "
            + "ROUND(((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population)) * 100, 2) AS NonCityPercentage "
            + "FROM country "
            + "LEFT JOIN city ON country.Capital = city.ID "
            + "WHERE country.Region = 'Southeast Asia' "
            + "GROUP BY country.Region";

    // Query to get population data for a specific country
    public static String query4 = "SELECT country.Name AS Name, "
            + "country.Population AS TotalPopulation, "
            + "SUM(city.Population) AS CityPopulation, "
            + "(country.Population - SUM(city.Population)) AS NonCityPopulation, "
            + "ROUND((SUM(city.Population) / country.Population) * 100, 2) AS CityPercentage, "
            + "ROUND(((country.Population - SUM(city.Population)) / country.Population) * 100, 2) AS NonCityPercentage "
            + "FROM country "
            + "LEFT JOIN city ON country.Capital = city.ID "
            + "WHERE country.Name = 'India' "
            + "GROUP BY country.Name, country.Population";

    // Query to get population data for a specific city
    public static String query5 = "SELECT city.Name AS Name, "
            + "city.Population AS TotalPopulation, "
            + "city.Population AS CityPopulation, "
            + "0 AS NonCityPopulation, "
            + "100 AS CityPercentage, "
            + "0 AS NonCityPercentage "
            + "FROM city "
            + "WHERE city.Name = 'Delhi'";
}
