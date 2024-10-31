# Goal in Context
As a data analyst, I want to retrieve a list of capital cities ordered by population (from largest to smallest) to provide comprehensive population insights on capital cities at global, continental, and regional levels.

# Scope
Business

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
1. Organization Requests Capital City Population Data.
2. Data Analyst Queries the Database.
3. System Retrieves and Orders Data.
4. Data Analyst Reviews and Delivers Report.

# Extensions
3. Incomplete Data for Some Capital Cities. 
    - i.  The data analyst notes the data limitations in the report and informs the organisation.

# Sub-Variations
1. All the capital cities in the world organised by largest population to smallest.
2. All the capital cities in a continent organised by largest population to smallest.
3. All the capital cities in a region organised by largest to smallest.
4. The top N populated capital cities in the world where N is provided by the user.
5. The top N populated capital cities in a continent where N is provided by the user.
6. The top N populated capital cities in a region where N is provided by the user.

# Schedule
Due Date: Release 1.2.0