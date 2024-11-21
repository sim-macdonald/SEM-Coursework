package com.napier.sem.queries;

//Class that contains the string for a language report sql query
public class Language_queries {

    public static String query = "SELECT countrylanguage.Language, " +
            "ROUND((SUM(countrylanguage.Percentage / 100 * country.Population) / (SELECT SUM(Population) FROM country)) * 100, 2) AS Population " +
            "FROM countrylanguage " +
            "JOIN country ON countrylanguage.CountryCode = country.Code " +
            "WHERE countrylanguage.Language IN ('Chinese', 'Hindi', 'Spanish', 'English', 'Arabic') " +
            "GROUP BY countrylanguage.Language " +
            "ORDER BY Population DESC";

}
