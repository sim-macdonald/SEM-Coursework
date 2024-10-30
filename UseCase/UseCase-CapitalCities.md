# Goal in Context
As a data analyst, I want to retrieve a list of capital cities ordered by population (from largest to smallest) to provide comprehensive population insights on capital cities at global, continental, and regional levels.

# Scope
Capital city population data collection and ordering by population size.

# Level
Primary task.

# Preconditions
Access to a database containing up-to-date population data for each capital city.

# Success End Condition
A report listing capital cities ordered by population from largest to smallest, either for the world or a specific continent or region, or the top N populated capital cities based on user input, is provided to the organization.

# Failed End Condition
No list is produced, or the data provided is inaccurate or incomplete.

# Primary Actor
Data Analyst.

# Trigger
A request for a capital city population report ordered by size is sent to the data analyst.

# Main Success Scenario
Organization Requests Capital City Population Data:
The organization requests a list of capital cities ordered by population at a specified level (global, continent, or region) and may specify a top N requirement.

Data Analyst Queries the Database:
The data analyst queries the database for capital city population data relevant to the requested level, either globally, by continent, or by region, with an option for the top N cities if specified.

System Retrieves and Orders Data:
The system retrieves the capital city population data and orders cities in descending order by population, filtering to the top N if needed.

Data Analyst Reviews and Delivers Report:
The data analyst reviews the ordered list for completeness and accuracy, then provides the report to the organization.

# Extensions
Incomplete Data for Some Capital Cities.

Condition: Population data is missing for certain capital cities within the specified scope.

Action: The data analyst notes this in the report and informs the organization of any data gaps.

# Sub-Variations
Variation 1: All Capital Cities in the World Ordered by Population
Details: System retrieves all capital cities globally and orders them by population, from largest to smallest.

Variation 2: All Capital Cities in a Continent Ordered by Population
Details: System retrieves all capital cities within a specified continent and orders them by population from largest to smallest.

Variation 3: All Capital Cities in a Region Ordered by Population
Details: System retrieves all capital cities within a specified region and orders them by population from largest to smallest.

Variation 4: Top N Populated Capital Cities in the World
Details: System retrieves and orders all capital cities by population globally, then filters to return the top N populated capital cities as specified by the user.

Variation 5: Top N Populated Capital Cities in a Continent
Details: System retrieves and orders all capital cities by population within a specified continent, then filters to return the top N populated capital cities as specified by the user.

Variation 6: Top N Populated Capital Cities in a Region
Details: System retrieves and orders all capital cities by population within a specified region, then filters to return the top N populated capital cities as specified by the user.

# Schedule
Due Date: Release 1.2.0