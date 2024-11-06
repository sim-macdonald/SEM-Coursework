package com.napier.sem;

import com.napier.sem.queries.Country_queries;
import com.napier.sem.reports.Country;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.util.ArrayList;

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
}