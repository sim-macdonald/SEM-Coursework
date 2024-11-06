package com.napier.sem;

import com.napier.sem.queries.Country_queries;
import com.napier.sem.queries.Population_queries;
import com.napier.sem.reports.Country;
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

        @Test
        void getPopulationReportTest() {
            // Test population report for a continent (e.g., Asia)
            Population report = db.getPopulationReport(Population_queries.query, 0, Population_queries.continent, "Asia");
            assertNotNull(report);
            assertEquals("Asia", report.getName());
            assertTrue(report.getTotalPopulation() > 0);
            assertTrue(report.getCityPopulation() >= 0);
            assertTrue(report.getNonCityPopulation() >= 0);
            assertEquals(report.getCityPercentage() + report.getNonCityPercentage(), 100, 0.01);
        }

    @Test
    void getPopulationReportRegionTest() {
        // Test population report for a region (e.g., Europe)
        Population report = db.getPopulationReport(Population_queries.query, 0, Population_queries.region, "Europe");
        assertNotNull(report);
        assertEquals("Europe", report.getName());
        assertTrue(report.getTotalPopulation() > 0);
    }

    @Test
    void getPopulationReportCountryTest() {
        // Test population report for a specific country (e.g., USA)
        Population report = db.getPopulationReport(Population_queries.query, 0, Population_queries.country, "USA");
        assertNotNull(report);
        assertEquals("USA", report.getName());
        assertTrue(report.getTotalPopulation() > 0);
    }
}
