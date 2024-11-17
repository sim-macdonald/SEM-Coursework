package com.napier.sem;

import com.napier.sem.queries.Country_queries;
import com.napier.sem.queries.Population_queries;
import com.napier.sem.reports.*;
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

//---------------------------------------------------------------------------------------------
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

    //-------------------------------------------------------------------------------------------

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
        db.printPopulationReport(null);
    }

    @Test
    void printPopulationTestEmpty()
    {
        ArrayList<Population> populationList = new ArrayList<Population>();
        db.printPopulationReport(populationList);
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
        ArrayList<Population> populationList = new ArrayList<Population>();
        populationList.add(null);
        db.printPopulationReport(populationList);
    }

    //-----------------------------------------------------------------------------------------------------------------

    @Test
    void printPopulation()
    {
        ArrayList<Population> populationList = new ArrayList<Population>();
        Population cou = new Population("Test",1000,500,500,50,50);
        populationList.add(cou);
        db.printPopulationReport(populationList);
    }

    //----------------------------------------------------------------------------------------------------------------------

    @Test
    void printLanguageTestNull()
    {
        db.printLanguage(null);
    }

    @Test
    void printLanguageTestEmpty()
    {
        ArrayList<Language> languages = new ArrayList<Language>();
        db.printLanguage(languages);
    }

    @Test
    void printLanguageTestContainsNull()
    {
        ArrayList<Language> lang = new ArrayList<Language>();
        lang.add(null);
        db.printLanguage(lang);
    }

    @Test
    void printLanguage()
    {
        ArrayList<Language> languages = new ArrayList<Language>();
        Language lang = new Language();
        lang.name = "ABC";
        lang.population = 11.11;
        languages.add(lang);
        db.printLanguage(languages);
    }
}
