# Goal in Context
As a data analyst, I want to access population data at various geographic levels (world, continent, region, country, district, and city), with breakdowns of populations living in cities and those not living in cities, to provide detailed demographic insights for the organization.

# Scope
Business

# Level
Primary task.

# Preconditions
Access to a reliable database containing accurate and up-to-date population data for each specified geographic level.

# Success End Condition
A comprehensive population report is generated that includes the total population, population in cities (with percentage), and population not in cities (with percentage) at the requested geographic level.

# Failed End Condition
No report is produced, or the data provided is inaccurate or incomplete.

# Primary Actor
Data Analyst.

# Trigger
A request for population data at a specified geographic level is sent to the data analyst.

# MAIN SUCCESS SCENARIO
1. Organization Requests Population Data.
2. Data Analyst Queries the Database.
3. System Retrieves and Calculates Data.
4. Data Analyst Reviews and Delivers Report.

# EXTENSIONS
3. Incomplete Data at Specified Level:
    - i. The data analyst notes the data limitations in the report and informs the organization.

# SUB-VARIATIONS
1. The population of people, people living in cities, and people not living in cities in each continent.
2. The population of people, people living in cities, and people not living in cities in each region.
3. The population of people, people living in cities, and people not living in cities in each country.
4. The population of the world.
5. The population of a continent.
6. The population of a region.
7. The population of a country.
8. The population of a district.
9. The population of a city.


# SCHEDULE
DUE DATE: Release 1.2.0