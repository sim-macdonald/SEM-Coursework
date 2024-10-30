# Goal in Context
As a data analyst, I want to retrieve a list of cities ordered by population (from largest to smallest) at multiple geographic levels (global, continent, region, country, district) to provide detailed urban demographic insights to the organization.

# Scope
City population data collection and ordering by population size.

# Level
Primary task.

# Preconditions
Access to a database containing up-to-date population data for each city.

# Success End Condition
A report listing cities ordered by population from largest to smallest, either for the world or a specific geographic level, or the top N populated cities based on user input, is provided to the organization.

# Failed End Condition
No list is produced, or the data provided is inaccurate or incomplete.

# Primary Actor
Data Analyst.

# Trigger
A request for a city population report ordered by size is sent to the data analyst.

# Main Success Scenario
Organization Requests City Population Data:
The organization requests a list of cities ordered by population for a specified level (global, continent, region, country, or district) and may specify a top N requirement.

Data Analyst Queries the Database:
The data analyst queries the database for population information at the requested geographic level, with an option to retrieve the top N populated cities if specified.

System Retrieves and Orders Data:
The system retrieves the city population data and orders cities in descending order by population, filtering to the top N if needed.

Data Analyst Reviews and Delivers Report:
The data analyst reviews the ordered list for completeness and accuracy, then provides the report to the organization.

# Extensions
Incomplete Data for Some Cities.

    1. Data analyst notifies the organisation that the data is incomplete.

# Sub-Variations
All the cities in the world organised by largest population to smallest.
All the cities in a continent organised by largest population to smallest.
All the cities in a region organised by largest population to smallest.
All the cities in a country organised by largest population to smallest.
All the cities in a district organised by largest population to smallest.
# Schedule
Due Date: Release 2.0
