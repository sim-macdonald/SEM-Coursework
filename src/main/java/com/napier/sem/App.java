package com.napier.sem;

import com.napier.sem.Database;

import java.sql.*;

public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        Database a = new Database();

        // Connect to database
        a.connect();

        // Disconnect from database
        a.disconnect();
    }
}