package com.napier.sem;

import com.napier.sem.queries.Country_queries;
import com.napier.sem.queries.Population_queries;
import com.napier.sem.reports.Capital_City;
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
    void printCapTestNull()
    {
        db.printCapital(null);
    }

    @Test
    void printCapTestEmpty()
    {
        ArrayList<Capital_City> cap = new ArrayList<Capital_City>();
        db.printCapital(cap);
    }

    @Test
    void printCapTestContainsNull()
    {
        ArrayList<Capital_City> cap = new ArrayList<Capital_City>();
        cap.add(null);
        db.printCapital(cap);
    }

    @Test
    void printCap()
    {
        ArrayList<Capital_City> cap = new ArrayList<Capital_City>();
        Capital_City c = new Capital_City();
        c.name = "TestName";
        c.Country="ASDF";
        c.population = 123;
        cap.add(c);
        db.printCapital(cap);
    }


}
