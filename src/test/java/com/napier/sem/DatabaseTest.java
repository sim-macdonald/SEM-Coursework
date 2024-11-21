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
    //create a test database
    static Database db;

    //Create a initialise the database
    @BeforeAll
    static void init()
    {
        db = new Database();
    }

//---------------------------------------------------------------------------------------------
    /**
     * Test case for the `printCountries` method when the input is `null`.
     * This test ensures that the method handles a `null` input.
     */
    @Test
    void printCountriesTestNull()
    {
        db.printCountries(null);
    }

    /**
     * Test case for the `printCountries` method when the input is an empty list.
     * This test ensures that the method handles an empty list correctly,
     * printing a message indicating no countries are found.
     */
    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        db.printCountries(countries);
    }

    /**
     * Test case for the `printCountries` method when the input list contains a `null` element.
     * This test ensures that the method can handle a list where one or more elements are `null`,
     * and continues processing the remaining valid elements or prints nothing if all are `null`.
     */
    @Test
    void printCountriesTestContainsNull()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(null);
        db.printCountries(countries);
    }

    /**
     * Test case for the `printCountries` method when the input contains a valid country.
     * This test ensures that the method correctly formats and prints a country's code, name, continent, region, population and capital.
     */
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
    void printPopulationTestNull()
    {
        db.printPopulationReport(null);
    }

    @Test
    void printPopulationTestContainsNull()
    {
        ArrayList<Population> population = new ArrayList<Population>();
        population.add(null);
        db.printPopulationReport(population);
    }

    @Test
    void printPopulation()
    {
        ArrayList<Population> populationList = new ArrayList<Population>();
        Population cou = new Population("Test",1000,500,500,50,50);
        populationList.add(cou);
        db.printPopulationReport(populationList);
    }

    @Test
    void printPopulationTestEmpty()
    {
        ArrayList<Population> populationList = new ArrayList<Population>();
        db.printPopulationReport(populationList);
    }

    //----------------------------------------------------------------------------------------------------------------------

    /**
     * Test case for the `printLanguage` method when the input is `null`.
     * This test ensures that the method handles a `null` input.
     */
    @Test
    void printLanguageTestNull()
    {
        db.printLanguage(null);
    }

    /**
     * Test case for the `printLanguage` method when the input is an empty list.
     * This test ensures that the method handles an empty list correctly,
     * printing a message indicating no languages are found.
     */
    @Test
    void printLanguageTestEmpty()
    {
        ArrayList<Language> languages = new ArrayList<Language>();
        db.printLanguage(languages);
    }

    /**
     * Test case for the `printLanguage` method when the input list contains a `null` element.
     * This test ensures that the method can handle a list where one or more elements are `null`,
     * and continues processing the remaining valid elements or prints nothing if all are `null`.
     */
    @Test
    void printLanguageTestContainsNull()
    {
        ArrayList<Language> lang = new ArrayList<Language>();
        lang.add(null);
        db.printLanguage(lang);
    }

    /**
     * Test case for the `printLanguage` method when the input contains a valid language.
     * This test ensures that the method correctly formats and prints a language's name and population.
     */
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
