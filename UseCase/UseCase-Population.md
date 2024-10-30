# Goal in Context
As a data analyst, I want to access population data at various geographic levels (world, continent, region, country, district, and city), with breakdowns of populations living in cities and those not living in cities, to provide detailed demographic insights for the organization.

# Scope
Population data retrieval across all relevant geographic levels, including urban versus rural distribution.

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
Organization Requests Population Data:
The organization requests population information for a specific geographic level (e.g., world, continent, region, country, district, city) and specifies whether they need details on populations living in cities versus not living in cities.

Data Analyst Queries the Database:
The data analyst queries the database for population information at the requested geographic level, including city versus non-city populations if specified.

System Retrieves and Calculates Data:
The system retrieves the total population data and calculates populations in cities and outside cities, providing the percentages of each where required.

Data Analyst Reviews and Delivers Report:
The data analyst reviews the processed data for completeness and accuracy, then delivers the report to the organization.

# EXTENSIONS
Incomplete Data at Specified Level.

Condition: Population data is incomplete for the requested level (e.g., missing city data for a specific region).

Action: The data analyst notes the data limitations in the report and informs the organization.

# SUB-VARIATIONS
Variation 1: Population of the World
Details: The system retrieves and returns only the total world population.

Variation 2: Population of Each Continent
Details: The system retrieves the total population of each continent, as well as the population living in cities (with percentage) and the population not living in cities (with percentage).

Variation 3: Population of Each Region
Details: The system retrieves the total population of each region, along with populations living in cities (with percentage) and not living in cities (with percentage).

Variation 4: Population of Each Country
Details: The system retrieves the total population of each country, including populations in cities (with percentage) and those not in cities (with percentage).

Variation 5: Population of a Specific Continent
Details: The system retrieves the population of a specific continent, including the number of people living in cities (with percentage) and not living in cities (with percentage).

Variation 6: Population of a Specific Region
Details: The system retrieves the population of a specified region, including populations in cities (with percentage) and not in cities (with percentage).

Variation 7: Population of a Specific Country
Details: The system retrieves the population of a specific country, including the breakdown of populations living in cities (with percentage) and those not living in cities (with percentage).

Variation 8: Population of a Specific District
Details: The system retrieves the total population of a specified district.

Variation 9: Population of a Specific City
Details: The system retrieves the total population of a specified city.

# SCHEDULE
DUE DATE: Release 1.2.0