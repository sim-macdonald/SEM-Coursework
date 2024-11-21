package com.napier.sem;

import com.napier.sem.queries.Language_queries;
import com.napier.sem.reports.Country;
import com.napier.sem.reports.Language;
import com.napier.sem.queries.Population_queries;
import com.napier.sem.reports.Population;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {
    //create a test database
    static Database db;
    static Database db2;

    //connect to test database
    @BeforeAll
    static void init()
    {
        db = new Database();
        db.connect("localhost:33060", 30000);
        db2 = new Database();

    }

    //disconnect from test database
    @AfterAll
    static void dc(){
        db.disconnect();
    }

    /**
     * Integration tests for connecting to the database.
     */
    //Is there a database
    @Test
    void testDatabaseExists() {

        assertNotNull(db, "Database connection should not be null after connecting");
    }

    //can connect to database
    @Test
    void testDatabaseConnection(){

        db2.connect("localhost:33060", 30000);
        assertNotNull(db, "Database connection should not be null after connecting");
    }

    //prints error message if connection fails
    @Test
    void testDatabaseConnectionFail(){

        db2.connect("localhost:33061", 1);
        assertNotNull(db, "Database connection should not be null after connecting");
    }

    //test disconnect
    @Test
    void testDisconnect() {
        db2.connect("localhost:33060", 30000);
        db2.disconnect();
    }


    //Country Tests
    //--------------------------------------------------------------------------------------

    /**
     * Integration test for retrieving a list of countries from the world.
     * Verifies that the `getCountryWorld` method works as expected.
     */
    @Test
    void testGetCountryWorld() {
        String query = "SELECT * FROM country";
        ArrayList<Country> countries = db.getCountryWorld(query, 5);

        // Verify the result is not null and list contains countries
        assertNotNull(countries, "The list of countries should not be null");
        assertFalse(countries.isEmpty(), "The list of countries should not be empty");

        Country country = countries.get(0);
        assertNotNull(country, "The first country should not be null");
        assertNotNull(country.name, "Country name should not be null");
        assertNotNull(country.code, "Country code should not be null");
    }

    /**
     * Integration test for handling invalid queries.
     * Verifies that the method gracefully handles invalid SQL queries.
     */
    @Test
    void testGetCountryWorldInvalidQuery() {
        String invalidQuery = "SELECT * FROM non_existing_table";
        ArrayList<Country> countries = db.getCountryWorld(invalidQuery, 5);

        // Assert that the result is null, as the query is invalid
        assertNull(countries, "The result should be null due to invalid query");
    }

    /**
     * Integration test for handling empty result sets.
     * Verifies that the method returns an empty list for queries that return no results.
     */
    @Test
    void testGetCountryWorldEmpty() {
        String query = "SELECT * FROM country WHERE Population < 0";
        ArrayList<Country> countries = db.getCountryWorld(query, 5);

        // Assert that the list is empty
        assertNotNull(countries, "The list should not be null");
        assertTrue(countries.isEmpty(), "The list should be empty due to no matching results");
    }

    /**
     * Integration test for retrieving countries from a specific region.
     * Verifies that the `getCountryRegion` works.
     */
    @Test
    void testGetCountryRegion() {
        String query = "SELECT * FROM country";
        String region = "North America";
        ArrayList<Country> countries = db.getCountryRegion(query, 5, region);  // Limit to 5 countries

        // Verify the result is not null and contains the correct region
        assertNotNull(countries, "The list of countries should not be null");
        assertFalse(countries.isEmpty(), "The list of countries should not be empty");

        for (Country country : countries) {
            assertEquals(region, country.region, "Country region should be " + region);
        }
    }

    /**
     * Integration test for handling invalid queries.
     * Verifies that the method gracefully handles invalid SQL queries.
     */
    @Test
    void testGetCountryRegionInvalidQuery() {
        String invalidQuery = "SELECT * FROM non_existing_table";
        String region = "North America";
        ArrayList<Country> countries = db.getCountryRegion(invalidQuery, 5, region);

        // Assert that the result is null, as the query is invalid
        assertNull(countries, "The result should be null due to invalid query");
    }

    /**
     * Integration test for handling empty result sets.
     * Verifies that the method returns an empty list for queries that return no results.
     */
    @Test
    void testGetCountryRegionEmpty() {
        String query = "SELECT * FROM country";
        String region = "North America AND population < 0";
        ArrayList<Country> countries = db.getCountryRegion(query, 5, region);

        // Assert that the list is empty
        assertNotNull(countries, "The list should not be null");
        assertTrue(countries.isEmpty(), "The list should be empty due to no matching results");
    }

    /**
     * Integration test for retrieving countries from a specific continent.
     * Verifies that the `getCountryContinent` method works.
     */
    @Test
    void testGetCountryContinent() {
        String query = "SELECT * FROM country";
        String continent = "Asia";
        ArrayList<Country> countries = db.getCountryContinent(query, 5, continent);  // Limit to 5 countries

        // Verify the result is not null and contains the correct continent
        assertNotNull(countries, "The list of countries should not be null");
        assertFalse(countries.isEmpty(), "The list of countries should not be empty");

        for (Country country : countries) {
            assertEquals(continent, country.continent, "Country continent should be " + continent);
        }
    }

    /**
     * Integration test for handling invalid queries.
     * Verifies that the method gracefully handles invalid SQL queries.
     */
    @Test
    void testGetCountryContinentInvalidQuery() {
        String invalidQuery = "SELECT * FROM non_existing_table";
        String continent = "Asia";
        ArrayList<Country> countries = db.getCountryContinent(invalidQuery, 5, continent);

        // Assert that the result is null, as the query is invalid
        assertNull(countries, "The result should be null due to invalid query");
    }

    /**
     * Integration test for handling empty result sets.
     * Verifies that the method returns an empty list for queries that return no results.
     */
    @Test
    void testGetCountryContinentEmpty() {
        String query = "SELECT * FROM country";
        String continent = "Asia AND population < 0";
        ArrayList<Country> countries = db.getCountryContinent(query, 5, continent);

        // Assert that the list is empty
        assertNotNull(countries, "The list should not be null");
        assertTrue(countries.isEmpty(), "The list should be empty due to no matching results");
    }

    //Language Tests - Simon
    //--------------------------------------------------------------------------------------------------

    /**
     * Integration test for retrieving a list of countries from the world.
     * Verifies that the `getLanguage` method works as expected.
     */
    @Test
    void testGetLanguage() {
        String query = Language_queries.query;
        ArrayList<Language> languages = db.getLanguages(query);

        // Verify the result is not null and list contains languages
        assertNotNull(languages, "The list of languages should not be null");
        assertFalse(languages.isEmpty(), "The list of languages should not be empty");

        Language language = languages.get(0);
        assertNotNull(language, "The first country should not be null");
        assertNotNull(language.name, "Language name should not be null");
        assertNotNull(language.population, "Language population should not be null");
    }

    /**
     * Integration test for handling invalid queries.
     * Verifies that the method gracefully handles invalid SQL queries.
     */
    @Test
    void testGetLanguageInvalidQuery() {
        String invalidQuery = "SELECT * FROM non_existing_table";
        ArrayList<Language> languages = db.getLanguages(invalidQuery);

        // Assert that the result is null, as the query is invalid
        assertNull(languages, "The result should be null due to invalid query");
    }

    /**
     * Integration test for handling empty result sets.
     * Verifies that the method returns an empty list for queries that return no results.
     */
    @Test
    void testGetLanguageEmpty() {
        String query = "SELECT countrylanguage.Language, " +
                "ROUND((SUM(countrylanguage.Percentage / 100 * country.Population) / (SELECT SUM(Population) FROM country)) * 100, 2) AS Population " +
                "FROM countrylanguage " +
                "JOIN country ON countrylanguage.CountryCode = country.Code " +
                "WHERE countrylanguage.Language IN ('Invalid') " +
                "GROUP BY countrylanguage.Language " +
                "ORDER BY Population DESC";;
        ArrayList<Language> languages = db.getLanguages(query);

        // Assert that the list is empty
        assertNotNull(languages, "The list should not be null");
        assertTrue(languages.isEmpty(), "The list should be empty due to no matching results");
    }

    //Population Tests
    //--------------------------------------------------------------------------------------

    @Test
    void testGetPopulationWorld() {
        // Query to get population data for the whole world
        String query = "SELECT 'World' AS Name, "
                + "SUM(country.Population) AS TotalPopulation, "
                + "SUM(city.Population) AS CityPopulation, "
                + "(SUM(country.Population) - SUM(city.Population)) AS NonCityPopulation, "
                + "ROUND((SUM(city.Population) / SUM(country.Population)) * 100, 2) AS CityPercentage, "
                + "ROUND(((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population)) * 100, 2) AS NonCityPercentage "
                + "FROM country "
                + "LEFT JOIN city ON country.Capital = city.ID";

        // Get the population report using the query
        ArrayList<Population> populationReport = db.getPopulationReport(query);

        // Ensure the report is not null and contains at least one entry
        assertNotNull(populationReport, "The population report should not be null");
        assertFalse(populationReport.isEmpty(), "The population report should contain data");

        // Check if the first report entry has valid data
        Population population = populationReport.get(0);
        assertNotNull(population.getName(), "The name should not be null");
        assertTrue(population.getTotalPopulation() > 0, "Total population should be greater than zero");
        assertTrue(population.getCityPopulation() >= 0, "City population should be non-negative");
        assertTrue(population.getNonCityPopulation() >= 0, "Non-city population should be non-negative");

        // Print the population report for visual verification
        db.printPopulationReport(populationReport);
    }

    @Test
    void testGetPopulationContinent() {
        // Query to get population data for a specific continent (e.g., Asia)
        String query = "SELECT country.Continent AS Name, "
                + "SUM(country.Population) AS TotalPopulation, "
                + "SUM(city.Population) AS CityPopulation, "
                + "(SUM(country.Population) - SUM(city.Population)) AS NonCityPopulation, "
                + "ROUND((SUM(city.Population) / SUM(country.Population)) * 100, 2) AS CityPercentage, "
                + "ROUND(((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population)) * 100, 2) AS NonCityPercentage "
                + "FROM country "
                + "LEFT JOIN city ON country.Capital = city.ID "
                + "WHERE country.Continent = 'Asia' "
                + "GROUP BY country.Continent";

        // Get the population report using the query
        ArrayList<Population> populationReport = db.getPopulationReport(query);

        // Ensure the report is not null and contains at least one entry
        assertNotNull(populationReport, "The population report should not be null");
        assertFalse(populationReport.isEmpty(), "The population report should contain data");

        // Check if the first report entry has valid data
        Population population = populationReport.get(0);
        assertNotNull(population.getName(), "The name should not be null");
        assertTrue(population.getTotalPopulation() > 0, "Total population should be greater than zero");
        assertTrue(population.getCityPopulation() >= 0, "City population should be non-negative");
        assertTrue(population.getNonCityPopulation() >= 0, "Non-city population should be non-negative");

        // Print the population report for visual verification
        db.printPopulationReport(populationReport);
    }

    @Test
    void testGetPopulationRegion() {
        // Query to get population data for a specific region (e.g., Southeast Asia)
        String query = "SELECT country.Region AS Name, "
                + "SUM(country.Population) AS TotalPopulation, "
                + "SUM(city.Population) AS CityPopulation, "
                + "(SUM(country.Population) - SUM(city.Population)) AS NonCityPopulation, "
                + "ROUND((SUM(city.Population) / SUM(country.Population)) * 100, 2) AS CityPercentage, "
                + "ROUND(((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population)) * 100, 2) AS NonCityPercentage "
                + "FROM country "
                + "LEFT JOIN city ON country.Capital = city.ID "
                + "WHERE country.Region = 'Southeast Asia' "
                + "GROUP BY country.Region";

        // Get the population report using the query
        ArrayList<Population> populationReport = db.getPopulationReport(query);

        // Ensure the report is not null and contains at least one entry
        assertNotNull(populationReport, "The population report should not be null");
        assertFalse(populationReport.isEmpty(), "The population report should contain data");

        // Check if the first report entry has valid data
        Population population = populationReport.get(0);
        assertNotNull(population.getName(), "The name should not be null");
        assertTrue(population.getTotalPopulation() > 0, "Total population should be greater than zero");
        assertTrue(population.getCityPopulation() >= 0, "City population should be non-negative");
        assertTrue(population.getNonCityPopulation() >= 0, "Non-city population should be non-negative");

        // Print the population report for visual verification
        db.printPopulationReport(populationReport);
    }

    @Test
    void testGetPopulationCountry() {
        // Query to get population data for a specific country (e.g., India)
        String query = "SELECT country.Name AS Name, "
                + "country.Population AS TotalPopulation, "
                + "SUM(city.Population) AS CityPopulation, "
                + "(country.Population - SUM(city.Population)) AS NonCityPopulation, "
                + "ROUND((SUM(city.Population) / country.Population) * 100, 2) AS CityPercentage, "
                + "ROUND(((country.Population - SUM(city.Population)) / country.Population) * 100, 2) AS NonCityPercentage "
                + "FROM country "
                + "LEFT JOIN city ON country.Capital = city.ID "
                + "WHERE country.Name = 'India' "
                + "GROUP BY country.Name, country.Population";

        // Get the population report using the query
        ArrayList<Population> populationReport = db.getPopulationReport(query);

        // Ensure the report is not null and contains at least one entry
        assertNotNull(populationReport, "The population report should not be null");
        assertFalse(populationReport.isEmpty(), "The population report should contain data");

        // Check if the first report entry has valid data
        Population population = populationReport.get(0);
        assertNotNull(population.getName(), "The name should not be null");
        assertTrue(population.getTotalPopulation() > 0, "Total population should be greater than zero");
        assertTrue(population.getCityPopulation() >= 0, "City population should be non-negative");
        assertTrue(population.getNonCityPopulation() >= 0, "Non-city population should be non-negative");

        // Print the population report for visual verification
        db.printPopulationReport(populationReport);
    }

    @Test
    void testGetPopulationCity() {
        // Query to get population data for a specific city (e.g., Mumbai)
        String query = "SELECT city.Name AS Name, "
                + "city.Population AS TotalPopulation, "
                + "city.Population AS CityPopulation, "
                + "0 AS NonCityPopulation, "
                + "100 AS CityPercentage, "
                + "0 AS NonCityPercentage "
                + "FROM city "
                + "WHERE city.Name = 'Mumbai'";

        // Get the population report using the query
        ArrayList<Population> populationReport = db.getPopulationReport(query);

        // Ensure the report is not null and contains at least one entry
        assertNotNull(populationReport, "The population report should not be null");
        assertFalse(populationReport.isEmpty(), "The population report should contain data");

        // Check if the first report entry has valid data
        Population population = populationReport.get(0);
        assertNotNull(population.getName(), "The name should not be null");
        assertTrue(population.getTotalPopulation() > 0, "Total population should be greater than zero");

        // Print the population report for visual verification
        db.printPopulationReport(populationReport);
    }


}


