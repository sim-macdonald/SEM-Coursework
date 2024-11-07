package com.napier.sem.queries;

public class Language_queries {

    String query = "SELECT countrylanguage.Language, SUM(countrylanguage.Percentage / 100 * country.Population) From countrylanguage JOIN country ON countrylanguage.Language = country.CountryLanguage.CountryCode = country.code ORDER BY Population DESC";
}
