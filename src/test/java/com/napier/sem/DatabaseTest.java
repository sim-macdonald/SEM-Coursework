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
    void printPopulationTestNull()
    {
        // Test when the list of countries is null
        db.printPopulation(null);
    }

    @Test
    void printPopulationTestEmpty()
    {
        // Test when the list of countries is empty
        ArrayList<Country> countries = new ArrayList<Country>();
        db.printPopulation(countries);
    }

    @Test
    void printPopulationTestContainsNull()
    {
        // Test when the list contains a null country object
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(null);
        db.printPopulation(countries);
    }

    @Test
    void printPopulationTestContainsZeroPopulation()
    {
        // Test when a country has a population of 0
        ArrayList<Country> countries = new ArrayList<Country>();
        Country cou = new Country();
        cou.code = "XYZ";
        cou.name = "ZeroPop";
        cou.continent = "TestContinent";
        cou.region = "TestRegion";
        cou.population = 0;
        cou.capital = "TestCapital";
        countries.add(cou);
        db.printPopulation(countries);
    }

    @Test
    void printPopulationTestContainsNegativePopulation()
    {
        // Test when a country has a negative population value (edge case)
        ArrayList<Country> countries = new ArrayList<Country>();
        Country cou = new Country();
        cou.code = "NEG";
        cou.name = "NegativePop";
        cou.continent = "TestContinent";
        cou.region = "TestRegion";
        cou.population = -1000;
        cou.capital = "TestCapital";
        countries.add(cou);
        db.printPopulation(countries);
    }

    @Test
    void printPopulationTestValidPopulation()
    {
        // Test when the country has a valid population value
        ArrayList<Country> countries = new ArrayList<Country>();
        Country cou = new Country();
        cou.code = "ABC";
        cou.name = "TestName";
        cou.continent = "TestContinent";
        cou.region = "TestRegion";
        cou.population = 1234567;
        cou.capital = "TestCapital";
        countries.add(cou);
        db.printPopulation(countries);
    }

}
