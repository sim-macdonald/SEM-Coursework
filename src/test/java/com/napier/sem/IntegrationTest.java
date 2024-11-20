package com.napier.sem;

import com.napier.sem.reports.Country;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {
    //create a test database
    static Database db;

    //connect to test database
    @BeforeAll
    static void init()
    {
        db = new Database();
        db.connect("localhost:33060", 30000);

    }

    //disconnect from test database
    @AfterAll
    static void dc(){
        db.disconnect();
    }

    /**
     * Integration test for connecting to the database.
     */
    @Test
    void testDatabaseConnection() {
        assertNotNull(db, "Database connection should not be null after connecting");
    }

    //--------------------------------------------------------------------------------------

    /**
     * Integration test for retrieving a list of countries from the world.
     * Verifies that the `getCountryWorld` method works as expected.
     */
    @Test
    void testGetCountryWorldNis5() {
        String query = "SELECT * FROM country";
        ArrayList<Country> countries = db.getCountryWorld(query, 5);

        assertNotNull(countries, "The list of countries should not be null");
        assertFalse(countries.isEmpty(), "The list of countries should not be empty");

        Country country = countries.get(0);
        assertNotNull(country, "The first country should not be null");
        assertNotNull(country.name, "Country name should not be null");
        assertNotNull(country.code, "Country code should not be null");
    }
}

