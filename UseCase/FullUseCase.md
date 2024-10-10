# Goal in Context
As a data analyst I want to produce a list of all the countries from largest population to smallest so that I can provide this information to the organisation.

# Scope
Global population data collection.

# Level
Primary task.

# Preconditions
We have access to a database that contains all the correct and current data of all countries.

# Success End Condition
A full list from largest to smallest countries by population is provided to the organisation.

# Failed End Condition
No list is produced or is incorrect.

# Primary Actor
Data Analyst.

# Trigger
A request for the query is sent to the data analyst.

# MAIN SUCCESS SCENARIO
Organisation requests the query on the list of countries.
Data Analyst runs the query and grabs all the populations of each countries.
Data Analyst receives the list in descending order.
Data Analyst gives this list to the organisation.

# EXTENSIONS
Population data doesn't exist for some countries:
Data analyst informs organisation that some population data doesn't exist.

# SUB-VARIATIONS
None.

# SCHEDULE
DUE DATE: Release 1.2.0