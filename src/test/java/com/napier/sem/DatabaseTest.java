package com.napier.sem;

import com.napier.sem.queries.Country_queries;
import com.napier.sem.queries.Population_queries;
import com.napier.sem.reports.Country;
import com.napier.sem.reports.City;
import com.napier.sem.reports.Population;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest
{
    static Database db;

    @BeforeAll
    static void init()
    {
        db = new Database();
    }

    @Test
    void printCountriesTestNull()
    {
        db.printCountries(null);
    }

    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        db.printCountries(countries);
    }

    @Test
    void printCountriesTestContainsNull()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(null);
        db.printCountries(countries);
    }

    @Test
    void printCountries()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country cou = new Country();
        cou.code = "ABC";
        cou.name = "TestName";
        cou.continent = "TestContinent";
        cou.region = "TestRegion";
        cou.population = 123;
        cou.capital = "TestCapital";
        countries.add(cou);
        db.printCountries(countries);
    }

    @Test
    void getCountryWorldValidQueries()
    {
        String query = "SELECT * FROM Country WHERE population > 0";
        int N = 0;
        db.getCountryWorld(query, N);
    }

    @Test
    void getCountryWorldValidQueriesWithLimit()
    {
        String query = "SELECT * FROM Country WHERE population > 0";
        int N = 5;
        db.getCountryWorld(query, N);
    }

    // Population report tests
    @Test
    void getPopulationReportTestContinent()
    {
        Population population = db.getPopulationReport("Continent", "Asia");
        assertNotNull(population, "Population report should not be null for a valid continent.");
        assertTrue(population.getTotalPopulation() > 0, "Total population should be greater than 0 for a valid continent.");
    }

    @Test
    void getPopulationReportTestRegion()
    {
        Population population = db.getPopulationReport("Region", "Southern Europe");
        assertNotNull(population, "Population report should not be null for a valid region.");
        assertTrue(population.getTotalPopulation() > 0, "Total population should be greater than 0 for a valid region.");
    }

    @Test
    void getPopulationReportTestCountry()
    {
        Population population = db.getPopulationReport("Country", "India");
        assertNotNull(population, "Population report should not be null for a valid country.");
        assertTrue(population.getTotalPopulation() > 0, "Total population should be greater than 0 for a valid country.");
    }

    @Test
    void getPopulationReportTestInvalidLevel()
    {
        Population population = db.getPopulationReport("InvalidLevel", "Asia");
        assertNull(population, "Expected null for an invalid level.");
    }

    @Test
    void printPopulationReportTestNull()
    {
        db.printPopulationReport(null);
    }

    @Test
    void printPopulationReportTestValidData()
    {
        Population population = new Population("TestArea", 1000000, 600000, 400000, 60.0, 40.0);
        db.printPopulationReport(population);
    }


    @Test
    void getCitiesByPopulationTestNullConnection() {
        db.disconnect();
        ArrayList<City> cities = db.getCitiesByPopulation();
        assertNull(cities, "Expected null when database connection is not established.");
    }

    @Test
    void getCitiesByPopulationTestEmptyDatabase() {
        db.connect();
        ArrayList<City> cities = db.getCitiesByPopulation();
        assertNotNull(cities, "Expected non-null empty list for cities in an empty database.");
        assertEquals(0, cities.size(), "Expected an empty list of cities when no data is present.");
    }

    @Test
    void getCitiesByPopulationTestValidData() {
        db.connect();
        ArrayList<City> cities = db.getCitiesByPopulation();
        assertNotNull(cities, "Expected a list of cities.");
        assertFalse(cities.isEmpty(), "Expected non-empty list of cities for populated database.");
    }


}
