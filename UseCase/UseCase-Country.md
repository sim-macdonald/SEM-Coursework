# Goal in Context
As a Data Analyst, I want to retrieve a list of countries ordered by population, from largest to smallest, so that I can create reports to inform better allocation of resources on a global and regional scale

# Scope
Business

# Level
Primary task.

# Preconditions
Access to a database that contains all the correct and current data of all countries.

# Success End Condition
A report listing countries ordered by population from largest to smallest, either for the world or a specific continent or region.

# Failed End Condition
No report is produced, or the data is inaccurate or incomplete. 

# Primary Actor
Data Analyst.

# Trigger
A request for a country report is sent to the data analyst.

# Main Success Scenario
1. Organisation requests the query on the list of countries.
2. Data Analyst runs the query and extracts all the populations of each country.
3. Data Analyst receives the list in descending order.
4. Data Analyst provides this list to the organisation.

# Extensions
3. Population data for country doesn't exist:
    - i. Data analyst informs organisation that some population data doesn't exist.

# Sub-Variations
1. All the countries in the world organised by largest population to smallest.
2. All the countries in a continent organised by largest population to smallest.
3. All the countries in a region organised by largest population to smallest.
4. The top N populated countries in the world where N is provided by the user.
5. The top N populated countries in a continent where N is provided by the user.
6. The top N populated countries in a region where N is provided by the user

# Schedule
Due Date: Release 2.0