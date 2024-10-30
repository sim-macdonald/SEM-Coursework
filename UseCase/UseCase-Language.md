# Goal in Context
As a data analyst, I want to retrieve the number of people who speak certain languages (Chinese, English, Hindi, Spanish, and Arabic) and calculate each language’s percentage of the world population so that I can provide detailed linguistic demographic information to the organization.

# Scope
Language population data collection and percentage calculation relative to the world population.

# Level
Primary task.

# Preconditions
Access to a database containing up-to-date population data and language speaker counts for each language.

# Success End Condition
A report listing the number of people who speak each specified language from largest to smallest, along with each language’s percentage of the world population, is provided to the organization.

# Failed End Condition
No list is produced, or the data provided is inaccurate or incomplete.

# Primary Actor
Data Analyst.

# Trigger
A request for a language speaker report ordered by population size is sent to the data analyst.

# Main Success Scenario
Organization Requests Language Population Data:
The organization requests data on the number of people who speak each specified language (Chinese, English, Hindi, Spanish, and Arabic), ordered from largest to smallest, including each language’s percentage of the world population.

Data Analyst Queries the Database:
The data analyst queries the database for the speaker population for each specified language and the total world population.

System Retrieves and Orders Data:
The system retrieves the population count for each language, calculates each as a percentage of the world population, and orders languages from the largest to smallest speaker population.

Data Analyst Reviews and Delivers Report:
The data analyst reviews the ordered list for accuracy, including each language’s speaker count and percentage of the world population, and provides the report to the organization.

# Extensions
Incomplete Data for Certain Languages.

Condition: Population data is missing for one or more languages.

Action: The data analyst notes this in the report and informs the organization of any data gaps.

# Sub-Variations
Variation 1: Retrieve Number of Chinese Speakers and World Population Percentage
Details: System retrieves the number of Chinese speakers, calculates their percentage of the world population, and includes this in the report.

Variation 2: Retrieve Number of English Speakers and World Population Percentage
Details: System retrieves the number of English speakers, calculates their percentage of the world population, and includes this in the report.

Variation 3: Retrieve Number of Hindi Speakers and World Population Percentage
Details: System retrieves the number of Hindi speakers, calculates their percentage of the world population, and includes this in the report.

Variation 4: Retrieve Number of Spanish Speakers and World Population Percentage
Details: System retrieves the number of Spanish speakers, calculates their percentage of the world population, and includes this in the report.

Variation 5: Retrieve Number of Arabic Speakers and World Population Percentage
Details: System retrieves the number of Arabic speakers, calculates their percentage of the world population, and includes this in the report.

# Schedule
Due Date: Release 1.2.0