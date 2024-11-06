package com.napier.sem.reports;

public class Population {

    public String name;
    public int totalPopulation;
    public int cityPopulation;
    public int nonCityPopulation;
    public double cityPercentage;
    public double nonCityPercentage;

    // Constructor
    public Population(String name, int totalPopulation, int cityPopulation, int nonCityPopulation, double cityPercentage, double nonCityPercentage) {
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.cityPopulation = cityPopulation;
        this.nonCityPopulation = nonCityPopulation;
        this.cityPercentage = cityPercentage;
        this.nonCityPercentage = nonCityPercentage;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getTotalPopulation() {
        return totalPopulation;
    }

    public int getCityPopulation() {
        return cityPopulation;
    }

    public int getNonCityPopulation() {
        return nonCityPopulation;
    }

    public double getCityPercentage() {
        return cityPercentage;
    }

    public double getNonCityPercentage() {
        return nonCityPercentage;
    }
}
