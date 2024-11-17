package com.napier.sem.queries;

public class Language_queries {

    public static String query = "SELECT countrylanguage.Language, SUM(countrylanguage.Percentage / 100 * country.Population) " +
            "FROM countrylanguage " +
            "JOIN country ON countrylanguage.CountryCode = country.code " +
            "WHERE countrylanguage.Language IN ('Chinese', 'Hindi', 'Spanish', 'English', 'Arabic') " +
            "GROUP BY countrylanguage.Language " +
            "ORDER BY SUM(countrylanguage.Percentage / 100 * country.Population) DESC";
}
