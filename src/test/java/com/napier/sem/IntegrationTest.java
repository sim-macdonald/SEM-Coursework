package com.napier.sem;

import com.napier.sem.queries.Language_queries;
import com.napier.sem.reports.Capital_City;
import com.napier.sem.reports.Country;
import com.napier.sem.reports.Language;
import com.napier.sem.queries.Population_queries;
import com.napier.sem.reports.Population;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import com.napier.sem.queries.City_queries;
import com.napier.sem.reports.City;
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
    /**
     * Integration test for retrieving population data for the whole world.
     *
     */
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

    /**
     * Integration test for retrieving population data for a specific continent.
     *
     */
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

    /**
     * Integration test for retrieving population data for a specific region.
     *
     */
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
    /**
     * Integration test for retrieving population data for a specific country.
     *
     */
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
    /**
     * Integration test for retrieving population data for a specific city.
     *
     */
    @Test
    void testGetPopulationCity() {
        // Query to get population data for a specific city
        String query = "SELECT city.Name AS Name, "
                + "city.Population AS TotalPopulation, "
                + "city.Population AS CityPopulation, "
                + "0 AS NonCityPopulation, "
                + "100 AS CityPercentage, "
                + "0 AS NonCityPercentage "
                + "FROM city "
                + "WHERE city.Name = 'Delhi'";

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

    /**
     * Integration test for retrieving population data for a specific district.
     */
    @Test
    void testGetPopulationDistrict() {
        // Query to get population data for a specific district
        String query = "SELECT city.District AS Name, "
                + "SUM(city.Population) AS TotalPopulation, "
                + "SUM(city.Population) AS CityPopulation, "
                + "0 AS NonCityPopulation, "
                + "100 AS CityPercentage, "
                + "0 AS NonCityPercentage "
                + "FROM city "
                + "WHERE city.District = 'Kabol' "
                + "GROUP BY city.District";

        // Get the population report using the query
        ArrayList<Population> populationReport = db.getPopulationReport(query);

        // Ensure the report is not null and contains at least one entry
        assertNotNull(populationReport, "The population report should not be null");
        assertFalse(populationReport.isEmpty(), "The population report should contain data");

        // Check if the first report entry has valid data
        Population population = populationReport.get(0);
        assertNotNull(population.getName(), "The district name should not be null");
        assertTrue(population.getTotalPopulation() > 0, "Total population should be greater than zero");

        // Print the population report for visual verification
        db.printPopulationReport(populationReport);
    }

    /**
     * Integration test for retrieving population data of people, people living in cities, and people not living in cities in each continent.
     *
     */
    @Test
    public void testPopulationByContinent() {
        String query = "SELECT country.Continent AS Name, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS CityPopulation, " +
                "(SUM(country.Population) - SUM(city.Population)) AS NonCityPopulation, " +
                "ROUND((SUM(city.Population) / SUM(country.Population)) * 100, 2) AS CityPercentage, " +
                "ROUND(((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population)) * 100, 2) AS NonCityPercentage " +
                "FROM country " +
                "LEFT JOIN city ON country.Capital = city.ID " +
                "GROUP BY country.Continent";

            ArrayList<Population> PopulationReport = db.getPopulationReport(query);

        // Verify the results
            assertNotNull(PopulationReport, "Population report should not be null.");
            assertFalse(PopulationReport.isEmpty(), "Population report should not be empty.");

            PopulationReport.forEach(continent -> {
                Population population = PopulationReport.get(0);
                assertNotNull(continent.getName(), "Continent name should not be null.");
                assertTrue(population.getTotalPopulation() > 0, "Total population should be greater than 0.");
                assertTrue(population.getCityPercentage() >= 0, "City percentage should be non-negative.");
                assertTrue(population.getNonCityPercentage() >= 0, "Non-city percentage should be non-negative.");
        });
    }

    /**
     * Integration test for retrieving population data of people, people living in cities, and people not living in cities in each region.
     *
     */
    @Test
    public void testPopulationByRegion() {
        String query = "SELECT country.Region AS Name, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS CityPopulation, " +
                "(SUM(country.Population) - SUM(city.Population)) AS NonCityPopulation, " +
                "ROUND((SUM(city.Population) / SUM(country.Population)) * 100, 2) AS CityPercentage, " +
                "ROUND(((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population)) * 100, 2) AS NonCityPercentage " +
                "FROM country " +
                "LEFT JOIN city ON country.Capital = city.ID " +
                "GROUP BY country.Region";

        ArrayList<Population> PopulationReport = db.getPopulationReport(query);

        // Verify the results
        assertNotNull(PopulationReport, "Population report should not be null.");
        assertFalse(PopulationReport.isEmpty(), "Population report should not be empty.");
        PopulationReport.forEach(region -> {
            Population population = PopulationReport.get(0);
            assertNotNull(region.getName(), "Region name should not be null.");
            assertTrue(population.getTotalPopulation() > 0, "Total population should be greater than 0.");
            assertTrue(population.getCityPercentage() >= 0, "City percentage should be non-negative.");
            assertTrue(population.getNonCityPercentage() >= 0, "Non-city percentage should be non-negative.");
        });
    }

    /**
     * Integration test for retrieving population data of people, people living in cities, and people not living in cities in each country.
     *
     */
    @Test
    public void testPopulationByCountry() {
        String query = "SELECT country.Name AS Name, " +
                "country.Population AS TotalPopulation, " +
                "SUM(city.Population) AS CityPopulation, " +
                "(country.Population - SUM(city.Population)) AS NonCityPopulation, " +
                "ROUND((SUM(city.Population) / country.Population) * 100, 2) AS CityPercentage, " +
                "ROUND(((country.Population - SUM(city.Population)) / country.Population) * 100, 2) AS NonCityPercentage " +
                "FROM country " +
                "LEFT JOIN city ON country.Capital = city.ID " +
                "GROUP BY country.Name, country.Population";

        ArrayList<Population> PopulationReport = db.getPopulationReport(query);

        // Verify the results
        assertNotNull(PopulationReport, "Population report should not be null.");
        assertFalse(PopulationReport.isEmpty(), "Population report should not be empty.");
        PopulationReport.forEach(country -> {
            Population population = PopulationReport.get(0);
            assertNotNull(country.getName(), "Country name should not be null.");
            assertTrue(population.getTotalPopulation() > 0, "Total population should be greater than 0.");
            assertTrue(population.getCityPercentage() >= 0, "City percentage should be non-negative.");
            assertTrue(population.getNonCityPercentage() >= 0, "Non-city percentage should be non-negative.");
        });
    }


    // Capital City Tests
//--------------------------------------------------------------------------------------

    /**
     * Integration test for retrieving all capital cities in the world by population.
     * Verifies that the `getCapitalCitiesByPopulation` method works as expected.
     */
    @Test
    void testGetAllCapitalCitiesWorld() {
        String query = "SELECT city.Name, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC";
        ArrayList<Capital_City> capitalCities = db.getCapitalCitiesByPopulation(query, 0);

        // Verify the result is not null and list contains capital cities
        assertNotNull(capitalCities, "The list of capital cities should not be null");
        assertFalse(capitalCities.isEmpty(), "The list of capital cities should not be empty");

        Capital_City capital = capitalCities.get(0);
        assertNotNull(capital, "The first capital city should not be null");
        assertNotNull(capital.name, "Capital city name should not be null");
        assertNotNull(capital.Country, "Capital city country name should not be null");
        assertTrue(capital.population > 0, "Capital city population should be greater than zero");
    }

    /**
     * Integration test for retrieving all capital cities in a continent by population.
     * Verifies that the `getCapitalCitiesByPopulation` method works as expected.
     */
    @Test
    void testGetAllCapitalCitiesContinent() {
        String continent = "Asia";
        String query = "SELECT city.Name, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Continent = '" + continent + "' " +
                "ORDER BY city.Population DESC";
        ArrayList<Capital_City> capitalCities = db.getCapitalCitiesByPopulation(query, 0);

        // Verify the result is not null and list contains capital cities
        assertNotNull(capitalCities, "The list of capital cities should not be null");
        assertFalse(capitalCities.isEmpty(), "The list of capital cities should not be empty");
    }

    /**
     * Integration test for retrieving all capital cities in a region by population.
     * Verifies that the `getCapitalCitiesByPopulation` method works as expected.
     */
    @Test
    void testGetAllCapitalCitiesRegion() {
        String region = "Eastern Europe";
        String query = "SELECT city.Name, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY city.Population DESC";
        ArrayList<Capital_City> capitalCities = db.getCapitalCitiesByPopulation(query, 0);

        // Verify the result is not null and list contains capital cities
        assertNotNull(capitalCities, "The list of capital cities should not be null");
        assertFalse(capitalCities.isEmpty(), "The list of capital cities should not be empty");
    }

    /**
     * Integration test for retrieving the top N populated capital cities in the world.
     * Verifies that the `getCapitalCitiesByPopulation` method works as expected.
     */
    @Test
    void testGetTopNCapitalCitiesWorld() {
        int topN = 5;
        String query = "SELECT city.Name, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC";
        ArrayList<Capital_City> capitalCities = db.getCapitalCitiesByPopulation(query, topN);

        // Verify the result is not null and contains exactly top N entries
        assertNotNull(capitalCities, "The list of capital cities should not be null");
        assertEquals(topN, capitalCities.size(), "The list should contain exactly " + topN + " entries");

        for (Capital_City capital : capitalCities) {
            assertNotNull(capital.name, "Capital city name should not be null");
            assertTrue(capital.population > 0, "Capital city population should be greater than zero");
        }
    }

    /**
     * Integration test for retrieving the top N populated capital cities in a continent.
     * Verifies that the `getCapitalCitiesByPopulation` method works as expected.
     */
    @Test
    void testGetTopNCapitalCitiesContinent() {
        int topN = 3;
        String continent = "Europe";
        String query = "SELECT city.Name, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Continent = '" + continent + "' " +
                "ORDER BY city.Population DESC";
        ArrayList<Capital_City> capitalCities = db.getCapitalCitiesByPopulation(query, topN);

        // Verify the result is not null and contains exactly top N entries
        assertNotNull(capitalCities, "The list of capital cities should not be null");
        assertEquals(topN, capitalCities.size(), "The list should contain exactly " + topN + " entries");
    }

    /**
     * Integration test for retrieving the top N populated capital cities in a region.
     * Verifies that the `getCapitalCitiesByPopulation` method works as expected.
     */
    @Test
    void testGetTopNCapitalCitiesRegion() {
        int topN = 2;
        String region = "Western Africa";
        String query = "SELECT city.Name, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = '" + region + "' " +
                "ORDER BY city.Population DESC";
        ArrayList<Capital_City> capitalCities = db.getCapitalCitiesByPopulation(query, topN);

        // Verify the result is not null and contains exactly top N entries
        assertNotNull(capitalCities, "The list of capital cities should not be null");
        assertEquals(topN, capitalCities.size(), "The list should contain exactly " + topN + " entries");
    }

    //City Tests
    //--------------------------------------------------------------------------------------

    /**
     * Integration test for retrieving a list of cities from the world.
     * Verifies that the `getCityWorld` method works as expected.
     */



    //@Test
    void testGetCityWorld() {
        String query = "SELECT * FROM city";
        ArrayList<City> cities = db.getCitiesByPopulation(query, 5);

        // Verify the result is not null and list contains countries
        assertNotNull(cities, "The list of cities should not be null");
        assertFalse(cities.isEmpty(), "The list of cities should not be empty");

        City city = cities.get(0);
        assertNotNull(city, "The first city should not be null");
        assertNotNull(city.name, "City ID should not be null");
        assertNotNull(city.countryCode, "City code should not be null");
    }


    /**
     * Integration test for handling invalid queries.
     * Verifies that the method gracefully handles invalid SQL queries.
     */


    @Test
    void testGetCityWorldInvalidQuery() {
        String invalidQuery = "SELECT * FROM non_existing_table";
        ArrayList<City> cities = db.getCitiesByPopulation(invalidQuery, 5);

        // Assert that the result is null, as the query is invalid
        assertNull(cities, "The result should be null due to invalid query");
    }


    /**
     * Integration test for handling empty result sets.
     * Verifies that the method returns an empty list for queries that return no results.
     */


    //@Test
    void testGetCityWorldEmpty() {
        String query = "SELECT * FROM city WHERE Population < 0";
        ArrayList<City> cities = db.getCitiesByPopulation(query, 5);

        // Assert that the list is empty
        assertNotNull(cities, "The list should not be null");
        assertTrue(cities.isEmpty(), "The list should be empty due to no matching results");
    }


   // @Test
    void testGetCityContinent() {
        String query = "SELECT * FROM city JOIN country ON city.ID = country.countryCode";
        String continent = "Africa";
        ArrayList<City> cities = db.getCityContinent(query, 5, continent);  // Limit to 5 countries

        // Verify the result is not null and contains the correct continent
        assertNotNull(cities, "The list of cities should not be null");
        assertFalse(cities.isEmpty(), "The list of cities should not be empty");

        for (City city : cities) {
            assertEquals(continent, city.name, "City continent should be " + continent);
        }
    }

    @Test
    void testGetCityContinentInvalidQuery() {
        String invalidQuery = "SELECT * FROM non_existing_table";
        String continent = "Asia";
        ArrayList<City> cities = db.getCityContinent(invalidQuery, 5, continent);

        // Assert that the result is null, as the query is invalid
        assertNull(cities, "The result should be null due to invalid query");
    }

    //@Test
    void testGetCityContinentEmpty() {
        String query = "SELECT * FROM city";
        String continent = "Asia AND population < 0";
        ArrayList<City> cities = db.getCityContinent(query, 5, continent);

        // Assert that the list is empty
        assertNotNull(cities, "The list should not be null");
        assertTrue(cities.isEmpty(), "The list should be empty due to no matching results");
    }


    //@Test
    void testGetCityDistrict() {
        String query = "SELECT * FROM city JOIN country ON city.ID = country.countryCode";
        String district = "Asia";
        ArrayList<City> cities = db.getCityDistrict(query, 5, district);  // Limit to 5 countries

        // Verify the result is not null and contains the correct continent
        assertNotNull(cities, "The list of cities should not be null");
        assertFalse(cities.isEmpty(), "The list of cities should not be empty");

        for (City city : cities) {
            assertEquals(district, city.name, "City continent should be " + district);
        }
    }

    @Test
    void testGetCityDistrictInvalidQuery() {
        String invalidQuery = "SELECT * FROM non_existing_table";
        String district = "invalid yes yes ooo";
        ArrayList<City> cities = db.getCityContinent(invalidQuery, 5, district);

        // Assert that the result is null, as the query is invalid
        assertNull(cities, "The result should be null due to invalid query");
    }

   // @Test
    void testGetCityDistrictEmpty() {
        String query = "SELECT * FROM city";
        String district = "Asia AND population < 0";
        ArrayList<City> cities = db.getCityContinent(query, 5, district);

        // Assert that the list is empty
        assertNotNull(cities, "The list should not be null");
        assertTrue(cities.isEmpty(), "The list should be empty due to no matching results");
    }


    //@Test
    void testGetCityCountry() {
        String query = "SELECT * FROM city JOIN country ON city.ID = country.countryCode";
        String country = "BRA";
        ArrayList<City> cities = db.getCityDistrict(query, 5, country);  // Limit to 5 countries

        // Verify the result is not null and contains the correct continent
        assertNotNull(cities, "The list of cities should not be null");
        assertFalse(cities.isEmpty(), "The list of cities should not be empty");

        for (City city : cities) {
            assertEquals(country, city.name, "City country should be " + country);
        }
    }

    @Test
    void testGetCityCountryInvalidQuery() {
        String invalidQuery = "SELECT * FROM non_existing_table";
        String country = "invalid yes yes ooo";
        ArrayList<City> cities = db.getCityCountry(invalidQuery, 5, country);

        // Assert that the result is null, as the query is invalid
        assertNull(cities, "The result should be null due to invalid query");
    }

    //@Test
    void testGetCityCountryEmpty() {
        String query = "SELECT * FROM city";
        String country = "BRA AND population < 0";
        ArrayList<City> cities = db.getCityContinent(query, 5, country);

        // Assert that the list is empty
        assertNotNull(cities, "The list should not be null");
        assertTrue(cities.isEmpty(), "The list should be empty due to no matching results");
    }

}


