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
    void testGetPopulationReportContinent() {
        db.connect();
        db.getPopulationReport("Continent", "Asia");
        db.disconnect();
    }

    @Test
    void testGetPopulationReportRegion() {
        db.connect();
        db.getPopulationReport("Region", "Southeast Asia");
        db.disconnect();
    }

    @Test
    void testGetPopulationReportCountry() {
        db.connect();
        db.getPopulationReport("Country", "India");
        db.disconnect();
    }

    @Test
    void testGetPopulationReportInvalid() {
        db.connect();
        db.getPopulationReport("Invalid", "XYZ");
        db.disconnect();
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
