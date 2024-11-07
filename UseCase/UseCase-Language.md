# Goal in Context
As a data analyst, I want to retrieve a list with the number of people who speak certain languages (Chinese, English, Hindi, Spanish, Arabic), including the percentage of the world's population that speaks that language, so that I can make my product accessable to the most amount of people.

# Scope
Business

# Level
Primary task.

# Preconditions
Access to a database that contains all the correct and current population data of specific language speakers.

# Success End Condition
A report listing the number of people who speak each specified language from largest to smallest, along with each language’s percentage of the world population.

# Failed End Condition
No report is produced, or the data is inaccurate or incomplete.

# Primary Actor
Data Analyst.

# Trigger
A request for a language report is sent to the data analyst.

# Main Success Scenario
1. Organisation requests a report on the number of people that speak specific languages, ordered from most to least.
2. Data Analyst runs a query and extracts all the populations that speaks specific languages.
3. Data Analyst receives the list in descending order.
4. Data Analyst provides this list to the organisation.

# Extensions
3. Population data for language doesn't exist:
    - i. Data analyst informs organisation that some language data doesn't exist.

# Sub-Variations
None.

# Schedule
Due Date: Release 2.0